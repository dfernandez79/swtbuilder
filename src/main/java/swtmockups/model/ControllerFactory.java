package swtmockups.model;

public interface ControllerFactory {
    ControllerFactory NULL = Object::new;

    Object create();
}
