
//class main {
//    public static void main(String[] args) {
//        Direction up = Direction.UP;
//        Direction right = Direction.RIGHT;
//        Direction left = Direction.LEFT;
//        Direction down = Direction.DOWN;
//        Robot robot = new Robot(0, 0, up);
//        Move move = new Move();
//
//        move.moveRobot(robot,-3,-4);
//    }
//}

class Move {
    public static void moveRobot(Robot robot, int toX, int toY) {
        robot.stepForward(); // your implementation here

//        System.out.println(robot.getDirection());
//        System.out.println(robot.getY());
//        System.out.println(robot.getX());

        // Robot is left of horizontal target (meaning he needs to move right until he gets there)
        if (robot.getX() < toX) {
            faceRobotNewDirection(robot, Direction.RIGHT);
            while (robot.getX() < toX) {
                robot.stepForward();
            }
        // Robot is right of horizontal target (meaning he needs to move left until he gets there)
        } else if (robot.getX() > toX) {
            faceRobotNewDirection(robot, Direction.LEFT);
            while (robot.getX() > toX) {
                robot.stepForward();
            }
        }
        // Robot is below horizontal target (meaning he needs to move up until he gets there)
        if (robot.getY() < toY) {
            faceRobotNewDirection(robot, Direction.UP);
            while (robot.getY() < toY) {
                robot.stepForward();
            }
        // Robot is above horizontal target (meaning he needs to move down until he gets there)
        } else if (robot.getY() > toY) {
            faceRobotNewDirection(robot, Direction.DOWN);
            while (robot.getY() > toY) {
                robot.stepForward();
            }
        }

//        System.out.println();
//        System.out.println(robot.getDirection());
//        System.out.println(robot.getY());
//        System.out.println(robot.getX());
    }

    public static void faceRobotNewDirection(Robot robot, Direction newDirection) {
        while (robot.getDirection() != newDirection) {
            robot.turnRight();
        }
    }

}


//Don't change code below

enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                throw new IllegalStateException();
        }
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void stepForward() {
        x += direction.dx();
        y += direction.dy();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}