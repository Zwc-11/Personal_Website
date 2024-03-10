package CS.Version1_L;

public class Test {
    public static void main(String[] args) {
        Problem p1 = new KineticFrictionProblem("Book", 1, 0.1);
        System.out.println(p1.getProblem());
        System.out.println(p1.getAnswer());

        Problem p2 = new SlowingDownProblem("Book", 10, 10, 1, 5);
        System.out.println(p2.getProblem());
        System.out.println(p2.getAnswer());

        Problem p3 = new SingleObjectProblem("Book", 10, 2, 0.2);
        System.out.println(p3.getProblem());
        System.out.println(p3.getAnswer());

        Problem p4 = new StartingFrictionProblem("Book", 10, 20, 0.1);
        System.out.println(p4.getProblem());
        System.out.println(p4.getAns());

        Problem p5 = new WallFrictionProblem("Book", 10, 0.1);
        System.out.println(p5.getProblem());
        System.out.println(p5.getAnswer());

        System.out.println();
        RandomProblem p6 = new RandomProblem(4);
        System.out.println(p6.getProblem());
        System.out.println(p6.getAnswer());

        System.out.println("<");
    }
}
