# Spring使用到的设计模式



#### 常见设计模式

Spring 框架使用了多种设计模式来实现其各个模块和功能，以下是一些常见的设计模式：

1. **依赖注入（Dependency Injection）：** 这是 Spring 框架最核心的设计模式之一。通过依赖注入，Spring 将对象之间的依赖关系从代码中解耦，使得系统更加灵活、可维护和可测试。依赖注入模式通常使用了工厂模式、策略模式等其他模式来实现。
2. **工厂模式（Factory Pattern）：** Spring 使用了工厂模式来创建和管理对象的生命周期。例如，BeanFactory 和 ApplicationContext 是 Spring 中最常用的工厂类，它们负责创建和管理 Bean 对象。
3. **单例模式（Singleton Pattern）：** Spring 中的 Bean 默认是单例的，即每个 Bean 在容器中只有一个实例。这样可以节省系统资源，并且确保 Bean 的状态是共享的。
4. **模板模式（Template Pattern）：** Spring 使用了模板模式来封装一些通用的操作，例如 JdbcTemplate、RestTemplate 等。这些模板类定义了一组通用的操作步骤，具体的实现由子类或回调方法提供。
5. **观察者模式（Observer Pattern）：** Spring 中的事件机制就是基于观察者模式实现的。通过 ApplicationEvent 和 ApplicationListener 接口，Spring 允许对象在特定事件发生时接收通知并执行相应的操作。
6. **代理模式（Proxy Pattern）：** Spring AOP（面向切面编程）功能就是基于代理模式实现的。通过代理对象，Spring 可以在目标对象的方法执行前后添加额外的逻辑，实现横切关注点的处理。
7. **适配器模式（Adapter Pattern）：** Spring 中的适配器模式主要体现在适配器设计模式和适配器类的使用上。例如，HandlerAdapter 用于适配不同类型的处理器（Handler）。
8. **装饰器模式（Decorator Pattern）：** Spring 中的 AOP 功能也可以使用装饰器模式来实现。通过动态代理技术，Spring 在运行时为目标对象生成代理对象，并在代理对象的方法中添加额外的功能。

以上是一些常见的设计模式在 Spring 框架中的应用。Spring 框架灵活地运用了设计模式，使得系统更加可扩展、可维护和可测试。



#### 适配器模式

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



#### SpringMVC的原理

Spring MVC 是 Spring 框架的一个模块，用于构建基于 Java 的 Web 应用程序。它基于 MVC（Model-View-Controller）设计模式，通过将请求处理流程划分为模型（Model）、视图（View）和控制器（Controller）三个组件来实现请求的处理和响应。

以下是 Spring MVC 的基本原理：

1. **前端控制器模式（Front Controller Pattern）：** Spring MVC 采用了前端控制器模式，所有的请求都由一个统一的控制器（DispatcherServlet）来接收和处理。DispatcherServlet 是 Spring MVC 的核心组件，它负责将请求分发给合适的处理器（Controller）进行处理。
2. **Handler Mapping：** DispatcherServlet 通过 Handler Mapping 将请求映射到合适的处理器（Controller）。Handler Mapping 是一个接口，用于根据请求的 URL 或其他条件确定处理器的映射关系。
3. **Handler Adapter：** 一旦确定了请求对应的处理器，DispatcherServlet 就会通过 Handler Adapter 来调用处理器的方法，并将请求的参数传递给处理器。Handler Adapter 是一个接口，用于适配不同类型的处理器。
4. **Handler Interceptor：** 在请求被处理器处理之前或之后，可以通过 Handler Interceptor 来进行拦截和处理。Handler Interceptor 是一个接口，允许在请求处理的各个阶段添加拦截器，并对请求进行预处理或后处理。
5. **ModelAndView：** 处理器处理请求后，通常会返回一个 ModelAndView 对象，其中包含了处理结果（Model）和视图的信息（View）。ModelAndView 将处理结果和视图的信息封装起来，传递给 DispatcherServlet。
6. **View Resolver：** DispatcherServlet 将 ModelAndView 中的视图信息交给 View Resolver 来解析，以确定具体的视图资源。View Resolver 是一个接口，用于根据视图名字解析出实际的视图资源（如 JSP 文件、Thymeleaf 模板等）。
7. **View Rendering：** 一旦确定了视图资源，DispatcherServlet 就会将 Model 中的数据传递给视图进行渲染，并最终返回给客户端。

综上所述，Spring MVC 的原理是通过 DispatcherServlet、Handler Mapping、Handler Adapter、Handler Interceptor、ModelAndView、View Resolver 等核心组件来处理请求，并通过 MVC 设计模式将请求的处理流程划分为模型、视图和控制器三个组件，实现了请求的处理和响应。