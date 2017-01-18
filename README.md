# SWT Builder

SWT Builder is an [internal DSL] for Java 8+ that simplifies the process of
creating user interfaces with [SWT].

## Install

### Manual

### Maven / Gradle

### Eclipse Update Site

## Getting started

To use the builder import the static methods of `SWTBuilder`.

The methods `createChildren` and `composite` helps you to define the components
and layout of a UI:

*  `createChildren`: add controls to an existing composite instance. Example:
    
    ```
    Shell shell = new Shell();
    shell.setSize(300, 40);
    createChildren(shell, c -> c.label().text("Hello World!"));
    ```

* `composite`: returns a builder to create a new composite instance and its
   child controls. Example:

    ```
    composite()
      .chidlren(c -> c.label().text("Hello World!"))
      .createControl(shell)
    ```

Both examples use a lambda expression. The argument `c` in those lambda
expressions is an instance of `CompositeBuilder`. 



[internal DSL]: http://todo
[SWT]: https://www.eclipse.org/swt/