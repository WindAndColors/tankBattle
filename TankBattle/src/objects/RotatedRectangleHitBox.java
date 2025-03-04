package objects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import java.util.ArrayList;
import java.util.List;

public class RotatedRectangleHitBox extends HitBox{
    private double rotationRadians;

    public RotatedRectangleHitBox(Coordinate coordinate, double width, double height, double rotationRadians) {
        super(coordinate, width, height);
        this.rotationRadians = rotationRadians;
    }

    public RotatedRectangleHitBox() {
        super();
    }

    public double getRotationRadians() {
        return rotationRadians;
    }

    public void setRotationRadians(double rotationRadians) {
        this.rotationRadians = rotationRadians;
    }

    @Override
    public CollisionInfo intersects(HitBox other) {
        return satIntersection((RotatedRectangleHitBox) other);
    }

    //分离轴检测碰撞
    private CollisionInfo satIntersection(RotatedRectangleHitBox other) {
        List<Vector2D> axes = getAxes();
        axes.addAll(other.getAxes());

        Vector2D smallestAxis = null;
        double minOverlap = Double.MAX_VALUE;

        for (Vector2D axis : axes) {
            Projection proj1 = project(axis);
            Projection proj2 = other.project(axis);

            if (!proj1.overlap(proj2)) {
                return new CollisionInfo(false, 0, null);
            } else {
                double overlap = proj1.getOverlap(proj2);
                if (overlap < minOverlap) {
                    minOverlap = overlap;
                    smallestAxis = axis;
                }
            }
        }

        Vector2D normal = smallestAxis;
        Vector2D centerConnect = new Vector2D(
                other.getCoordinate().getX() - this.getCoordinate().getX(),
                other.getCoordinate().getY() - this.getCoordinate().getY()
        );

        //如果法线方向与中心连线方向在同一侧，则反转法线
        if (normal.dot(centerConnect) > 0) {
            normal = normal.negate();
        }

        return new CollisionInfo(true, minOverlap, normal);
    }

    //获取投影轴（分离轴的垂直轴）
    private List<Vector2D> getAxes() {
        List<Vector2D> axes = new ArrayList<>();
        double radian = rotationRadians;
        // 两条边的单位方向向量
        Vector2D edge1 = new Vector2D(Math.cos(radian), Math.sin(radian));
        Vector2D edge2 = new Vector2D(-Math.sin(radian), Math.cos(radian));
        axes.add(edge1.perp());
        axes.add(edge2.perp());
        return axes;
    }

    //图形在某一轴上的投影长度
    private Projection project(Vector2D axis) {
        List<Point2D.Double> vertices = getVertices();
        double min = Double.MAX_VALUE;
        double max = -Double.MAX_VALUE;
        for (Point2D.Double vertex : vertices) {
            double proj = (vertex.x * axis.getX()) + (vertex.y * axis.getY());
            if (proj < min) min = proj;
            if (proj > max) max = proj;
        }
        return new Projection(min, max);
    }

    //获得图形的顶点
    private List<Point2D.Double> getVertices() {
        List<Point2D.Double> vertices = new ArrayList<>();
        double halfWidth = getWidth() / 2.0;
        double halfHeight = getHeight() / 2.0;

        // 未旋转前的四个顶点（相对于中心点）
        double[][] corners = {
                {-halfWidth, -halfHeight},
                {halfWidth, -halfHeight},
                {halfWidth, halfHeight},
                {-halfWidth, halfHeight}
        };

        // 创建旋转变换矩阵
        AffineTransform transform = new AffineTransform();
        transform.translate(getCoordinate().getX(), getCoordinate().getY()); // 平移到世界坐标
        transform.rotate(rotationRadians); // 绕中心旋转

        // 对每个顶点应用变换
        for (double[] corner : corners) {
            Point2D.Double point = new Point2D.Double(corner[0], corner[1]);
            Point2D.Double transformedPoint = new Point2D.Double();
            transform.transform(point, transformedPoint); // 应用旋转和平移
            vertices.add(transformedPoint);
        }
        return vertices;
    }

    @Override
    public Shape getShape() {
        Path2D path = new Path2D.Double();
        List<Point2D.Double> vertices = getVertices();

        if (!vertices.isEmpty()) {
            // 按顶点顺序构建路径
            path.moveTo(vertices.get(0).x, vertices.get(0).y);
            for (int i = 1; i < vertices.size(); i++) {
                path.lineTo(vertices.get(i).x, vertices.get(i).y);
            }
            path.closePath();
        }
        return path;
    }
}
