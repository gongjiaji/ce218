package game2;

public class RotateNShoot implements Controller {
    private Action action = new Action();

    @Override
    public Action action() {
        action.shoot_s = false;
        return action;
    }
}
