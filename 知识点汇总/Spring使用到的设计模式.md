# Spring使用到的设计模式

## 适配器模式



1. **适配器接口：**
   - 在 Spring 中，`Adapter` 接口通常是一个用于适配的接口。例如，`HandlerAdapter` 就是一个适配器接口，它定义了用于适配处理器（`Handler`）的方法。
2. **HandlerAdapter：**
   - `HandlerAdapter` 是 Spring MVC 中用于适配处理器的接口。它定义了将请求映射到处理器的方式。Spring MVC 中的各种处理器（`Controller`）可以选择实现这个接口，以适应不同的处理器类型。
3. **MessageListenerAdapter：**
   - `MessageListenerAdapter` 是 Spring 的 JMS（Java Message Service）中的适配器。它用于将消息监听器适配为能够处理特定消息的 `MessageListener`。
4. **RequestToViewNameTranslator：**
   - 这是一个用于将请求映射到视图名的适配器接口。不同的实现可以适应不同的映射策略。例如，`DefaultRequestToViewNameTranslator` 就是其中的一个实现。
5. **HandlerExceptionResolver：**
   - `HandlerExceptionResolver` 用于将异常映射到处理器适配器，以便提供全局的异常处理。它允许适配器将异常适配为特定的视图或其他响应。
6. **WebArgumentResolverAdapter：**
   - 在 Spring MVC 中，`WebArgumentResolverAdapter` 是一个适配器，用于将自定义的 `WebArgumentResolver` 适配为 `HandlerMethodArgumentResolver`，从而支持更多的方法参数解析策略。
7. **InitializingBeanAdapter：**
   - `InitializingBeanAdapter` 是一个适配器，用于将实现了 `InitializingBean` 接口的对象适配为 `SmartInitializingSingleton`，以便在所有单例 Bean 实例化完成后执行特定的初始化逻辑。

这些适配器类允许 Spring 框架通过适配器模式灵活地适应不同的接口或实现，从而提供一些可插拔的、可定制的特性。适配器设计模式有助于实现松耦合，使得系统更易于维护和扩展。