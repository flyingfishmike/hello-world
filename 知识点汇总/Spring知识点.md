# Spring知识点



#### Bean的加载过程：

1. **定位：** Spring容器通过配置文件或注解等方式定位需要加载的Bean。
2. **加载：** Spring容器根据配置信息实例化Bean，这包括调用构造函数来创建Bean实例。
3. **设置属性：** Spring容器将配置的属性注入到Bean中，这可以通过构造函数注入、Setter方法注入或字段注入实现。
4. **Aware接口回调：** 如果Bean实现了相应的Aware接口，Spring容器将相应的资源注入到Bean中，例如ApplicationContextAware、BeanNameAware等。
5. **BeanPostProcessor处理：** 如果配置了BeanPostProcessor，Spring容器将在初始化前后调用相应的方法，允许定制Bean的初始化和销毁过程。
6. **初始化：** 如果Bean实现了InitializingBean接口，Spring容器将调用其`afterPropertiesSet`方法，或者通过配置文件中的`init-method`属性指定的初始化方法。
7. **生命周期：** Bean现在处于可用状态，可以被应用程序使用。
8. **使用：** 应用程序可以使用从Spring容器中获取的Bean执行操作。
9. **销毁：** 如果Bean实现了DisposableBean接口，Spring容器将在Bean被销毁前调用其`destroy`方法，或者通过配置文件中的`destroy-method`属性指定的销毁方法。



#### Bean的生命周期：

Spring Bean的生命周期包括三个主要阶段：

1. **实例化阶段：** 创建Bean的实例。
2. **属性赋值阶段：** 设置Bean的属性值。
3. **初始化和销毁阶段：** 在Bean初始化完成后和销毁之前执行相应的回调方法。



#### Bean的类型：

1. **Singleton（单例）：** 在整个应用中只存在一个Bean实例，由Spring容器负责管理。
2. **Prototype（原型）：** 每次请求Bean时，容器都会创建一个新的实例。
3. **Request：** 每次HTTP请求都会创建一个新的Bean实例（仅在Web应用中有效）。
4. **Session：** 在用户会话中创建一个Bean实例（仅在Web应用中有效）。
5. **Global Session：** 在全局会话中创建一个Bean实例，仅在使用portlet上下文时有效。
6. **Application：** 在ServletContext中创建一个Bean实例（仅在Web应用中有效）。
7. **Custom Scope：** 自定义的Bean作用域。

不同类型的Bean适用于不同的场景，选择合适的Bean类型有助于更好地管理和组织应用程序的组件。



#### 获取Bean的方式:



在Spring框架中，获取Bean的方式有多种，主要包括：

1. **通过类型获取：**

   ```
   javaCopy code
   MyBean myBean = context.getBean(MyBean.class);

   ```

   这种方式是通过Bean的类型直接获取，适用于只有一个该类型的Bean存在于Spring容器中的情况。

2. **通过名称获取：**

   ```
   javaCopy code
   MyBean myBean = (MyBean) context.getBean("myBean");

   ```

   这种方式是通过Bean的名称获取，适用于存在多个Bean，但是只有一个Bean的名称为"myBean"的情况。

3. **通过类型和名称获取：**

   ```
   javaCopy code
   MyBean myBean = context.getBean("myBean", MyBean.class);

   ```

   这种方式是结合了类型和名称的检索，确保获取到的Bean满足指定的类型和名称。

4. **通过工厂方法获取：**

   如果Bean是通过工厂方法创建的，可以通过以下方式获取：

   ```
   javaCopy code
   MyBean myBean = context.getBean(MyBeanFactory.class).createMyBean();

   ```

   其中，`MyBeanFactory` 是负责创建 `MyBean` 实例的工厂类。

5. **通过Bean的别名获取：**

   ```
   javaCopy code
   MyBean myBean = context.getBean("aliasName", MyBean.class);

   ```

   可以为Bean设置别名，通过别名获取Bean。

6. **通过条件获取Bean：**

   使用`getBean(Class<T> requiredType, Object... args)` 方法，传入类型和其他条件，可以根据条件获取Bean。

   ```
   javaCopy code
   MyBean myBean = context.getBean(MyBean.class, "arg1", "arg2");

   ```

这些是一些获取Bean的常见方式，具体选择哪种方式取决于应用程序的需求和设计。在实际应用中，根据具体情况选择合适的获取Bean的方式。





#### bean的加载方式：



在Spring框架中，有多种方式可以加载Bean，取决于您的项目需求和偏好。以下是一些常见的Bean加载方式：

1. **XML配置方式：** 使用XML配置文件定义Bean的配置信息，然后通过Spring的ApplicationContext容器加载和管理这些Bean。

```
xmlCopy code
<bean id="myBean" class="com.example.MyBean" />

```

1. **基于Java配置的方式：** 使用Java类来配置Bean的定义，通常使用@Configuration和@Bean注解来完成。

```
javaCopy code
@Configuration
public class AppConfig {
    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}

```

1. **基于注解的方式：** 使用注解标记Bean类和依赖关系，Spring会自动扫描并加载这些Bean。常见的注解包括@Component、@Service、@Repository、@Controller等。

```
javaCopy code
@Component
public class MyBean {
    // ...
}

```

1. **通过Java代码手动注册方式：** 使用Java代码手动创建和注册Bean到Spring容器中。

```
javaCopy code
GenericApplicationContext context = new GenericApplicationContext();
context.registerBean(MyBean.class);
context.refresh();

```

1. **使用XML配置和Java配置混合方式：** 可以同时使用XML配置文件和Java配置类来定义和加载Bean。
2. **通过XML的命名空间扩展方式：** Spring提供了各种命名空间扩展，如context、aop、tx等，用于定义特定类型的Bean，如数据源、事务管理器等。

```
xmlCopy code
<tx:annotation-driven />

```

1. **通过扫描包来自动检测和注册Bean方式：** Spring可以自动扫描指定包下的类，并将它们注册为Bean。

```
xmlCopy code
<context:component-scan base-package="com.example" />

```

1. **使用Spring Boot的自动配置方式：** Spring Boot项目中，许多Bean可以通过自动配置来加载，无需显式配置。

这些都是常见的Bean加载方式，您可以根据项目的需求和设计选择适合的方式。Spring的灵活性和可扩展性使得您可以根据具体情况来定义和加载Bean。





#### beandFactory和factoryBean区别:



BeanFactory
BeanFactory，以Factory结尾，表示它是一个工厂(接口)， 它负责生产和管理bean的一个工厂。在Spring中，BeanFactory是工厂的顶层接口，也是IOC容器的核心接口，因此BeanFactory中定义了管理Bean的通用方法，如 getBean 和 containsBean 等，它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。BeanFactory只是个接口，并不是IOC容器的具体实现，所以Spring容器给出了很多种实现，如 DefaultListableBeanFactory、XmlBeanFactory、ApplicationContext等，其中XmlBeanFactory就是常用的一个，该实现将以XML方式描述组成应用的对象及对象间的依赖关系。



FactoryBean

        FactoryBean是一个Bean，但又不仅仅是一个Bean。它是一个能生产或修饰对象生成的工厂Bean，类似于设计模式中的工厂模式和装饰器模式。它能在需要的时候生产一个对象，且不仅仅限于它自身，它能返回任何Bean的实例。一个Bean如果实现了FactoryBean接口，那么根据该Bean的名称获取到的实际上是getObject返回的对象，而不是这个Bean自身实例，如果要获取这个Bean自身实例，那么需要在名称前面加上'&'符号。


#### BeanFactory与ApplicationContext的区别:

BeanFactory是Spring框架的基础设施，面向Spring本身。ApplicationContext则面向使用Spring框架的开发者，几乎所有的应用场合都可以直接使用ApplicationContext，而非底层的BeanFactory。

另外，ApplicationContext的初始化和BeanFactory有一个重大的区别：

BeanFactory在初始化容器时，并未实例化Bean，直到第一次访问某个Bean时才实例目标Bean。这样，我们就不能发现一些存在的Spring的配置问题。如果Bean的某一个属性没有注入，BeanFacotry加载后，直至第一次使用调用getBean方法才会抛出异常。

而ApplicationContext则在初始化应用上下文时就实例化所有单实例的Bean，相对应的，ApplicationContext的初始化时间会比BeanFactory长一些。



#### ApplicationContext的四个实现类:

Spring 框架提供了多个 `ApplicationContext` 接口的实现类，每个实现类适用于不同的场景和配置方式。以下是四个常见的 `ApplicationContext` 实现类：

1. **ClassPathXmlApplicationContext：**

   - 这是通过 XML 文件配置 Spring 容器的实现类。它从类路径下的 XML 文件中加载 bean 配置信息。

   ```
   javaCopy code
   ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

   ```

2. **FileSystemXmlApplicationContext：**

   - 与 `ClassPathXmlApplicationContext` 类似，不同之处在于它从文件系统路径下的 XML 文件中加载 bean 配置信息。

   ```
   javaCopy code
   ApplicationContext context = new FileSystemXmlApplicationContext("/path/to/applicationContext.xml");

   ```

3. **AnnotationConfigApplicationContext：**

   - 这是通过 Java Config 类进行配置的实现类。它允许在类上使用 `@Configuration` 注解，通过 `@Bean` 注解声明 bean。

   ```
   javaCopy code
   ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

   ```

4. **GenericApplicationContext：**

   - 这是一个通用的实现类，可以通过编程方式注册 bean。它不依赖于特定的配置方式，可以用于灵活的自定义场景。

   ```
   javaCopy code
   GenericApplicationContext context = new GenericApplicationContext();
   context.registerBean(MyService.class);
   context.refresh();

   ```

这些实现类可以根据不同的需求选择。如果使用 XML 进行配置，可以选择 `ClassPathXmlApplicationContext` 或 `FileSystemXmlApplicationContext`。如果使用 Java Config 进行配置，可以选择 `AnnotationConfigApplicationContext`。如果希望通过编程方式注册 bean，可以选择 `GenericApplicationContext`。



#### 自动装配



Spring Boot 的自动装配是通过 `@EnableAutoConfiguration` 注解来实现的。这个注解通常放在应用的主配置类上（通常是 `Application` 类），它隐式地定义了一组自动配置类，这些类根据项目的依赖和环境自动启用或禁用一些配置。

自动装配的过程涉及以下关键组件：

1. **@EnableAutoConfiguration：**
   - 这个注解通常放在主配置类上，它会触发 Spring Boot 的自动配置机制。这个注解本身包含了 `@AutoConfigurationPackage` 和 `@Import(AutoConfigurationImportSelector.class)`。
2. **@AutoConfigurationPackage：**
   - 这个注解用于注册基本的包路径，以便将主配置类所在的包及其子包中的所有类都纳入到 Spring Boot 的组件扫描范围。
3. **AutoConfigurationImportSelector：**
   - 这个类实现了 `ImportSelector` 接口，负责选择自动配置类。它会从 `META-INF/spring.factories` 文件中读取 `EnableAutoConfiguration` 键对应的值，这些值指定了要启用的自动配置类。
4. **spring.factories 文件：**
   - 这个文件位于 `META-INF` 目录下，其中包含了多个配置项。对于自动配置来说，关键的配置项是 `EnableAutoConfiguration`，它列举了多个自动配置类的全限定类名。Spring Boot 在启动时会根据这些配置加载对应的自动配置类。
5. **自动配置类（xxxAutoConfiguration）：**
   - 这些类包含了一系列的 `@Bean` 声明，用于配置应用的各种组件。这些配置类通常根据类路径上的条件（例如，类路径中存在某个特定的类或者某个 bean 已经定义）来确定是否应该生效。

总体来说，Spring Boot 的自动装配通过一系列的配置类和条件来实现，根据项目的依赖和环境动态地决定使用哪些配置。这使得开发者可以更轻松地搭建和配置应用，同时也提供了很大的灵活性，可以根据需要禁用某些自动配置或者添加自定义的配置。



#### 循环依赖解决方法

1. **构造函数注入：**

   - 避免在构造函数中注入循环依赖。因为在对象构造的过程中，依赖关系还未完全建立，可能导致使用尚未完全初始化的对象。

2. **Setter 方法注入：**

   - 使用 Setter 方法注入依赖，而不是在构造函数中注入。Setter 方法注入是在对象构造完成之后调用的，可以避免在构造函数中出现循环依赖的问题。

3. **@Lazy 注解：**

   - 使用 `@Lazy` 注解延迟加载 bean。这样，当容器初始化 bean 时，如果发现循环依赖，会先创建一个代理对象，等到真正需要使用时再去初始化。

     ```
     javaCopy code
     @Component
     @Lazy
     public class A {
         private B b;

         @Autowired
         public A(B b) {
             this.b = b;
         }
     }

     @Component
     @Lazy
     public class B {
         private A a;

         @Autowired
         public B(A a) {
             this.a = a;
         }
     }

     ```

4. **使用接口或者AOP代理：**

   - 将循环依赖的类抽象成接口，然后使用接口注入，而不是具体的类。或者使用 AOP 代理，将其中一个类的引用通过代理来获取，以解决循环依赖的问题。

   ```
   javaCopy code
   public interface A {
       void method();
   }

   @Component
   public class AImpl implements A {
       private B b;

       @Autowired
       public AImpl(B b) {
           this.b = b;
       }

       // ...
   }

   @Component
   public class B {
       private A a;

       @Autowired
       public B(A a) {
           this.a = a;
       }

       // ...
   }

   ```

5.Spring 使用了三级缓存的机制来解决循环依赖的问题。这个机制包含了三个缓存：

1. **singletonObjects：**
   - 这个缓存用于存储完全初始化并准备好的单例对象。当一个单例 bean 完全初始化后，会放入这个缓存。
2. **earlySingletonObjects：**
   - 这个缓存用于存储被 `ObjectFactory` 包装的提前暴露的单例对象。`ObjectFactory` 是一个函数式接口，它定义了一个获取对象的方法。在 Spring 中，`ObjectFactory` 主要用于解决循环依赖的问题。当一个 bean 还没有完全初始化时，会被包装成一个 `ObjectFactory` 对象，并放入这个缓存。
3. **singletonFactories：**
   - 这个缓存用于存储用于创建单例对象的工厂。当一个 bean 还没有完全初始化时，会被包装成一个工厂对象，并放入这个缓存。

整个循环依赖的解决流程可以简要描述为：

1. 当容器启动时，会初始化并放入 `singletonObjects` 缓存的那个 bean A，但是不会初始化它的依赖 bean B。
2. 当初始化 bean A 时，发现需要依赖 B，此时会创建一个 B 的 `ObjectFactory` 对象，并放入 `earlySingletonObjects` 缓存。
3. 接下来，容器尝试初始化 bean B。在初始化 B 的过程中，发现需要依赖 A，此时会尝试从 `singletonObjects` 缓存中获取 A，但是 A 还没有初始化完成。
4. 为了解决这个问题，Spring 会使用 `ObjectFactory` 对象来提供一个 B 的实例，这时 B 会被放入 `singletonObjects` 缓存中。
5. 最后，A 的初始化完成，并且容器中的两个 bean 都被正确地缓存，解决了循环依赖的问题。

这个三级缓存的机制确保了循环依赖问题的解决，并且能够在大多数情况下正确工作。但是，由于这个机制的复杂性，也可能带来一些潜在的问题，例如，如果你在构造函数中注入循环依赖，可能会得到一个尚未完成初始化的对象。因此，最好避免在构造函数中注入循环依赖。

这些方法都有各自的适用场景，具体的选择取决于项目的架构和业务逻辑。在使用这些方法时，要注意避免引入不必要的复杂性和性能开销。



#### 拦截器和过滤器的区别

拦截器（Interceptor）和过滤器（Filter）是在 Java Web 开发中用于处理请求的两种不同的机制，它们有以下主要区别：

1. **所处位置：**
   - **拦截器：** 拦截器是 Spring 框架中的一部分，用于拦截处理程序执行之前和之后的请求。它由 Spring MVC 容器管理，与 Spring MVC 框架的其他组件密切相关。
   - **过滤器：** 过滤器是 Java Servlet 规范中的一部分，用于在 Servlet 容器中对请求进行预处理和后处理。它是 Web 应用中的一部分，与任何特定的框架无关，可以在不同的 Web 框架中使用。
2. **依赖对象：**
   - **拦截器：** 拦截器依赖于 Spring MVC 框架，因此只能应用于 Spring MVC 框架中的请求处理过程。
   - **过滤器：** 过滤器是在 Servlet 容器级别上执行的，与任何 Web 框架无关，可以应用于任何 Java Web 应用中。
3. **作用范围：**
   - **拦截器：** 拦截器只对 Spring MVC 中的请求进行拦截，可以在 Controller 方法执行前后进行处理，也可以应用于所有的请求或者只针对特定的请求路径。
   - **过滤器：** 过滤器可以拦截 Web 应用中的所有请求，包括静态资源的请求、JSP 页面的请求等。
4. **执行顺序：**
   - **拦截器：** 在 Spring MVC 中，可以定义多个拦截器，并且可以通过配置来控制它们的执行顺序。拦截器的执行顺序可以由开发者控制。
   - **过滤器：** 在 Servlet 容器中，过滤器的执行顺序由其在 web.xml 文件中的声明顺序决定。
5. **功能特点：**
   - **拦截器：** 拦截器更适合对请求进行处理、修改、重定向等操作，并且可以访问 Spring MVC 中的一些上下文信息和资源。
   - **过滤器：** 过滤器更适合对请求进行预处理和后处理，例如字符编码、权限检查、日志记录等。

总的来说，拦截器和过滤器都是用于对请求进行处理的机制，但是它们所处的位置、作用范围、功能特点等方面有一些不同。在实际开发中，可以根据具体的需求选择使用拦截器还是过滤器。