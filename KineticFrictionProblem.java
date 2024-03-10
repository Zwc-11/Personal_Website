package CS.Version1_L;

public class KineticFrictionProblem extends Problem {
    KineticFrictionProblem(String object, double mass, double ckf) {
        super(Problem.KINETIC_FRICTION);

        String problem = "A " + mass + "kg " + object
                + " slides across a surface. If the coefficient of kinetic friction is "
                + ckf + ", what is the frictional force the object experiences?";

        double gravitationalForce = mass * gStrength;
        double normalForce = gravitationalForce;
        double frictionalForce = normalForce * ckf;
        frictionalForce = Math.round(frictionalForce * 100.0) / 100.0;

        String fullSolution = "m = " + mass + "kg, μₖ = " + ckf + ", Fₖ = ? <br><br>"
                + "Fₖ = μₖFₙ <br>"
                + "= μₖF₉ <br>"
                + "= μₖmg <br>"
                + "= (" + ckf + ")(" + mass + "kg)(9.8N/kg) <br>"
                + "= " + frictionalForce + "N <br><br>"
                + "Therefore, the frictional force is " + frictionalForce + "N";

        setProblem(problem);
        setFbd(frictionalForce, 0, normalForce, gravitationalForce);
        setAnswer(frictionalForce);
        setFullSolution(fullSolution);
    }
}
