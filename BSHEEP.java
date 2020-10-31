import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
 
class Main {
 
  public static void main(String[] args) {
    FasterScanner sc = new FasterScanner(System.in);
 
    int t = sc.nextInt();
 
    while (t-- > 0) {
      int n = sc.nextInt();
 
      Point2D[] points = new Point2D[n];
      for (int i = 0; i < n; i++) {
        int x = sc.nextInt();
        int y = sc.nextInt();
        points[i] = new Point2D(x, y, i + 1);
      }
      GrahamScan graham = new GrahamScan(points);
 
      Point2D[] arr = graham.hull();
 
      // compute circumference
      double dist = 0;
      for (int i = 0; i < arr.length; i++) {
        int j = (i + 1) % arr.length;
        dist += arr[i].distanceTo(arr[j]);
      }
 
      System.out.printf("%.2f\n", dist);
 
      for (Point2D p : arr)
        System.out.print(p + " ");
      System.out.println("\n");
    }
  }
}
 
 
class GrahamScan {
  private Deque<Point2D> hull = new ArrayDeque<Point2D>();
 
  public GrahamScan(Point2D[] points) {
    // defensive copy
    int n = points.length;
    Point2D[] a = new Point2D[n];
    for (int i = 0; i < n; i++) {
      a[i] = points[i];
    }
 
    // Sort so that a[0] is positioned bottommost and as
    // far to the left as possible, as required by the problem's
    // output description (i.e., sort by lowest y-coordinate;
    // break ties by x-coordinate and, if necessary, index, to
    // handle possible duplicate points.
    Arrays.sort(a);
 
    // sort by polar angle with respect to base point a[0],
    // breaking ties by distance to a[0]
    Arrays.sort(a, 1, n, a[0].polarOrder());
 
    hull.push(a[0]); // a[0] is first extreme point
 
    // find index k1 of first point not equal to a[0]
    int k1;
    for (k1 = 1; k1 < n; k1++)
      if (!a[0].equals(a[k1]))
        break;
    if (k1 == n)
      return; // all points equal
 
    // find index k2 of first point not collinear with a[0] and a[k1]
    int k2;
    for (k2 = k1 + 1; k2 < n; k2++)
      if (Point2D.ccw(a[0], a[k1], a[k2]) != 0)
        break;
    hull.push(a[k2 - 1]); // a[k2-1] is second extreme point
 
    // Graham scan
    for (int i = k2; i < n; i++) {
      Point2D top = hull.pop();
      while (Point2D.ccw(hull.peek(), top, a[i]) <= 0) {
        top = hull.pop();
      }
      hull.push(top);
      hull.push(a[i]);
    }
  }
 
  public Point2D[] hull() {
    Point2D[] s = new Point2D[hull.size()];
    int i = s.length - 1;
    for (Point2D p : hull)
      s[i--] = p;
    return s;
  }
 
}
 
 
class Point2D implements Comparable<Point2D> {
 
  private final double x; // x coordinate
  private final double y; // y coordinate
  private final int idx;
 
  public Point2D(double x, double y, int idx) {
    this.x = x;
    this.y = y;
    this.idx = idx;
  }
 
  public double x() {
    return x;
  }
 
  public double y() {
    return y;
  }
 
  /**
   * @return { -1, 0, +1 } if a->b->c is a { clockwise, collinear,
   *         counterclockwise } turn.
   */
  public static int ccw(Point2D a, Point2D b, Point2D c) {
    double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    if (area2 < 0)
      return -1;
    else if (area2 > 0)
      return +1;
    return 0;
  }
 
  public static double area2(Point2D a, Point2D b, Point2D c) {
    return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
  }
 
  public double distanceTo(Point2D that) {
    double dx = this.x - that.x;
    double dy = this.y - that.y;
    return Math.sqrt(dx * dx + dy * dy);
  }
 
  /**
   * Compares two points by y-coordinate, breaking ties by x-coordinate,
   * and, if necessary, idx, to handle possible duplicate points per
   * problem description.
   */
  public int compareTo(Point2D that) {
    if (this.y < that.y)
      return -1;
    if (this.y > that.y)
      return +1;
    if (this.x < that.x)
      return -1;
    if (this.x > that.x)
      return +1;
    if (this.idx < that.idx)
      return -1;
 
    return +1;
  }
 
  /**
   * Compares two points by polar angle (between 0 and 2pi) with respect to
   * this point.
   */
  public Comparator<Point2D> polarOrder() {
    return new PolarOrder();
  }
 
  private class PolarOrder implements Comparator<Point2D> {
    public int compare(Point2D q1, Point2D q2) {
      double dx1 = q1.x - x;
      double dy1 = q1.y - y;
      double dx2 = q2.x - x;
      double dy2 = q2.y - y;
 
      if (dy1 >= 0 && dy2 < 0)
        return -1; // q1 above; q2 below
      else if (dy2 >= 0 && dy1 < 0)
        return +1; // q1 below; q2 above
      else if (dy1 == 0 && dy2 == 0) { // 3-collinear and horizontal
        if (dx1 >= 0 && dx2 < 0)
          return -1;
        else if (dx2 >= 0 && dx1 < 0)
          return +1;
        else
          return 0;
      } else if (dx1 == dx2 && dy1 == dy2)
        // same point; break tie with idx
        return (q2.idx - q1.idx);
 
      return -ccw(Point2D.this, q1, q2); // both above or below
    }
  }
 
  @Override
  public boolean equals(Object other) {
    if (other == this)
      return true;
    if (other == null)
      return false;
    if (other.getClass() != this.getClass())
      return false;
    Point2D that = (Point2D) other;
    return this.x == that.x && this.y == that.y;
  }
 
  /**
   * we are required to output points by index.
   */
  @Override
  public String toString() {
    return idx + "";
  }
 
}
 
 
class FasterScanner {
  private InputStream mIs;
  private byte[] buf = new byte[1024];
  private int curChar;
  private int numChars;
 
  public FasterScanner() {
    this(System.in);
  }
 
  public FasterScanner(InputStream is) {
    mIs = is;
  }
 
  public int read() {
    if (numChars == -1)
      throw new InputMismatchException();
    if (curChar >= numChars) {
      curChar = 0;
      try {
        numChars = mIs.read(buf);
      } catch (IOException e) {
        throw new InputMismatchException();
      }
      if (numChars <= 0)
        return -1;
    }
    return buf[curChar++];
  }
 
  public String nextLine() {
    int c = read();
    while (isSpaceChar(c))
      c = read();
    StringBuilder res = new StringBuilder();
    do {
      res.appendCodePoint(c);
      c = read();
    } while (!isEndOfLine(c));
    return res.toString();
  }
 
  public String nextString() {
    int c = read();
    while (isSpaceChar(c))
      c = read();
    StringBuilder res = new StringBuilder();
    do {
      res.appendCodePoint(c);
      c = read();
    } while (!isSpaceChar(c));
    return res.toString();
  }
 
  public long nextLong() {
    int c = read();
    while (isSpaceChar(c))
      c = read();
    int sgn = 1;
    if (c == '-') {
      sgn = -1;
      c = read();
    }
    long res = 0;
    do {
      if (c < '0' || c > '9')
        throw new InputMismatchException();
      res *= 10;
      res += c - '0';
      c = read();
    } while (!isSpaceChar(c));
    return res * sgn;
  }
 
  public int nextInt() {
    int c = read();
    while (isSpaceChar(c))
      c = read();
    int sgn = 1;
    if (c == '-') {
      sgn = -1;
      c = read();
    }
    int res = 0;
    do {
      if (c < '0' || c > '9')
        throw new InputMismatchException();
      res *= 10;
      res += c - '0';
      c = read();
    } while (!isSpaceChar(c));
    return res * sgn;
  }
 
  public boolean isSpaceChar(int c) {
    return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
  }
 
  public boolean isEndOfLine(int c) {
    return c == '\n' || c == '\r' || c == -1;
  }
 
}
