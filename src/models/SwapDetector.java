package models;

public class SwapDetector {
    private ISequenceDetector detector;

    public SwapDetector(ISequenceDetector detector) {
        this.detector = detector;
    }

    public boolean check(GameField field, Point point1, Point point2) {
        if (point1.getX() < 0 || point1.getY() < 0 || point2.getX() < 0 || point2.getY() < 0) {
            return false;
        }
        if (point1.getX() >= field.getWidth() || point2.getX() >= field.getWidth() || point1.getY() >= field.getHeight() || point2.getY() >= field.getHeight()) {
            return false;
        }
        if (!(Math.abs(point1.getX() - point2.getX()) == 1 && Math.abs(point1.getY() - point2.getY()) == 1)) {
            if (Math.abs(point1.getX() - point2.getX()) < 2 && Math.abs(point1.getY() - point2.getY()) < 2) {
                try {
                    field.swap(point1, point2);
                    var sequences = detector.search(field);
                    return sequences.size() > 0;
                } finally {
                    field.swap(point1, point2);
                }
            }
        }
        return false;
    }
}
