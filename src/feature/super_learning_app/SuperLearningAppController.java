package feature.super_learning_app;

import core.controller.Controller;
import core.model.Facade;

public class SuperLearningAppController extends Controller {

    public SuperLearningAppController(Facade facade, SuperLearningApp superLearningApp) {
        super(facade);
    }
    
    // @Override
    public boolean enableUIView() {
        return true;
    }

    // @Override
    public boolean disableUIView() {
        return true;
    }
}
