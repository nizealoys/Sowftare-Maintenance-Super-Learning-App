public class Main {
    public static void main(String[] args) {
        Feature root = new Feature("Vehicle", true, null);
        Feature engine = new Feature("Engine", true, "or");
        Feature electric = new Feature("Electric", false, null);
        Feature gas = new Feature("Gas", false, null);

        root.add(engine);
        engine.add(electric);
        engine.add(gas);

        electric.activate();
        System.out.println("Electric actif ? " + electric.isActive());
        System.out.println("Gas actif ? " + gas.isActive());

        gas.activate();   // doit afficher un conflit XOR
        System.out.println("Gas actif ? " + gas.isActive());
    }
}