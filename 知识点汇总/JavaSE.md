# JavaSE知识



#### 死锁

死锁是指两个或多个进程（或线程）相互等待对方持有的资源而无法继续执行的状态。在并发程序中，死锁是一种常见的问题，它可能发生的原因有多种，以下是一些典型的死锁产生的情形：

1. **互斥条件：**
   - 进程对资源进行排他性控制，即一次只有一个进程可以使用某个资源。如果一个进程在持有某个资源的同时请求其他资源，并且其他资源已经被其他进程占用，就可能导致死锁。
2. **请求和保持条件：**
   - 进程已经保持了至少一个资源，但又提出了新的资源请求，而该资源可能被其他进程持有，这样就可能发生死锁。
3. **不剥夺条件：**
   - 进程已经获得了某个资源，在其自愿释放之前，其他进程无法剥夺它。如果一个进程持有了某个资源并请求其他资源，而其他资源已被其他进程占用，就可能导致死锁。
4. **循环等待条件：**
   - 一组进程形成循环等待资源的关系。每个进程都在等待下一个进程所持有的资源，从而形成一个循环。如果这个循环的每个进程都持有了一个资源并等待另一个进程的资源，就可能导致死锁。

死锁产生的典型示例是两个或多个进程相互等待对方释放资源。例如：

```
javaCopy code
// 进程1
synchronized (resource1) {
    // 一些操作
    synchronized (resource2) {
        // 一些操作
    }
}

// 进程2
synchronized (resource2) {
    // 一些操作
    synchronized (resource1) {
        // 一些操作
    }
}

```

在上述代码中，如果进程1获得了`resource1`并等待`resource2`，而进程2获得了`resource2`并等待`resource1`，它们就会陷入死锁状态。

防止死锁的方法通常包括合理设计资源分配策略、使用超时机制、破坏循环等待条件、以及通过死锁检测和恢复来处理死锁。



#### String的存放位置

在 Java 8 及之后的版本中，字符串常量池的管理方式发生了一些变化。在这之前，字符串常量池是放在永久代（Permanent Generation）中的，而从 Java 8 开始，永久代被移除，取而代之的是元空间（Metaspace）。

在 Java 8 之后，<u>字符串常量池被移到了堆中</u>，而不再是永久代或元空间。这是因为永久代被认为是一个容易导致内存溢出的区域，而将字符串常量池移到堆中更好地适应动态应用程序的需求。

具体而言，字符串常量池的存储位置在 Java 堆（Heap）的一部分，称为字符串区。字符串区的划分是为了更好地支持 Java 8 引入的字符串常量池优化，即字符串常量池中的字符串不再复制一份到堆中，而是直接存储在堆上。

需要注意的是，Java 中的字符串（`java.lang.String`）本身是不可变的，而字符串常量池中存储的字符串是唯一的，这意味着相同的字符串在常量池中只会存在一份。这种设计提高了字符串的共享性，有助于节省内存。



堆、栈、方法区 
JAVA的JVM的内存可分为3个区：堆(heap)、栈(stack)和方法区(method) 

堆区: 
1.存储的全部是对象，每个对象都包含一个与之对应的class的信息。(class的目的是得到操作指令) 
2.jvm只有一个堆区(heap)被所有线程共享，堆中不存放基本类型和对象引用，只存放对象本身 
栈区: 
1.每个线程包含一个栈区，栈中只保存基础数据类型的对象和自定义对象的引用(不是对象)，对象都存放在堆区中 
2.每个栈中的数据(原始类型和对象引用)都是私有的，其他栈不能访问。 
3.栈分为3个部分：基本类型变量区、执行环境上下文、操作指令区(存放操作指令)。 
方法区: 
1.又叫静态区，跟堆一样，被所有的线程共享。方法区包含所有的class和static变量。 
2.方法区中包含的都是在整个程序中永远唯一的元素，如class，static变量。 



#### JVM内存结构

Java Virtual Machine (JVM)内存模型描述了Java程序在运行时分配内存的方式。JVM内存主要分为以下几个区域：

1. **程序计数器（Program Counter Register）:**
   - 每个线程都有一个程序计数器，它用于指示当前线程执行的字节码指令的地址。在多线程环境下，切换线程时，程序计数器存储了当前线程正在执行的位置，确保线程切换后能够正确恢复执行。
2. **Java虚拟机栈（Java Virtual Machine Stacks）:**
   - 每个线程都有自己的Java虚拟机栈，用于存储局部变量、方法参数、部分返回值和操作数栈等。每个方法在执行的时候都会创建一个栈帧，用于存储方法的局部变量和中间结果。栈帧在方法调用完成后会被弹出。
3. **本地方法栈（Native Method Stack）:**
   - 与Java虚拟机栈类似，本地方法栈用于执行本地（非Java）方法。在使用JNI（Java Native Interface）调用本地方法时，会使用本地方法栈。
4. **Java堆（Java Heap）:**
   - Java堆是JVM管理的最大一块内存，用于存储对象实例。所有线程共享Java堆，在堆上进行垃圾回收操作。堆的大小可以通过JVM的启动参数进行调整。
5. **方法区（Method Area）:**
   - 方法区用于存储类的信息、常量、静态变量、即时编译器编译后的代码等。在较新的JVM规范中，方法区被称为元空间（Metaspace），并且不再有固定大小的限制，它的大小受限于系统的实际内存。
6. **运行时常量池（Runtime Constant Pool）:**
   - 运行时常量池是方法区的一部分，用于存储编译时期生成的各种字面量和符号引用。运行时常量池使得在运行期间能够通过符号引用直接获取运行时需要用到的常量。
7. **直接内存（Direct Memory）:**
   - 直接内存并不是JVM规范中定义的内存区域，但是它被划分出来，主要用于NIO（New I/O）库的缓冲区，通过与本地代码协调分配堆外内存，可以在一些场景中提高性能。

JVM内存模型的设计考虑了多线程、垃圾回收等因素，确保Java程序能够在不同平台上稳定高效地运行。不同的垃圾回收算法和内存管理策略可以通过JVM的启动参数进行配置。



#### 使用List集合要注意的问题



1. **线程安全性：**

   - `ArrayList` 和 `LinkedList` 等常见的 `List` 实现类是非线程安全的。如果在多线程环境中使用，要么采用同步机制来保证线程安全，要么考虑使用线程安全的 `Vector` 类或者 `Collections.synchronizedList()` 方法包装。

     ```
     javaCopy code
     List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());

     ```

2. **Null 元素：**

   - `List` 允许存储 `null` 元素。当对列表进行遍历或其他操作时，务必注意可能存在的 `null` 元素，以防止 `NullPointerException`。

3. **顺序保证：**

   - 不同的 `List` 实现可能对元素的存储和访问顺序有不同的保证。`ArrayList` 保证元素按照插入的顺序存储，而 `LinkedList` 也保持了元素的插入顺序，但在随机访问时性能较差。选择合适的实现类要根据具体的需求。

4. **性能考虑：**

   - 对于频繁的随机访问和更新操作，`ArrayList` 的性能通常比 `LinkedList` 好。但是，对于频繁的插入和删除操作，`LinkedList` 通常更高效。

5. **equals 和 hashCode：**

   - 如果自定义类的对象存储在 `List` 中，确保正确实现了 `equals` 和 `hashCode` 方法。这是因为 `List` 的一些方法，如 `contains`、`indexOf`、`remove` 等，依赖于这两个方法。

6. **ConcurrentModificationException：**

   - 在迭代过程中，不要直接使用 `List` 的 `remove` 操作，以避免 `ConcurrentModificationException`。可以使用迭代器的 `remove` 方法或者通过新的 `List` 进行元素删除。

     ```
     javaCopy code
     List<String> list = new ArrayList<>();
     Iterator<String> iterator = list.iterator();
     while (iterator.hasNext()) {
         String element = iterator.next();
         if (someCondition) {
             iterator.remove(); // 正确的删除方式
         }
     }

     ```

7. **避免频繁扩容：**

   - 在使用 `ArrayList` 时，如果知道列表的大小，可以使用 `ArrayList` 的构造函数指定初始容量，以避免频繁扩容带来的性能开销。

     ```
     javaCopy code
     List<String> list = new ArrayList<>(100); // 指定初始容量为 100

     ```



#### 保证存入顺序的集合

在 Java 中，`LinkedHashMap` 是一种可以保持元素插入顺序的 `Map` 实现。它继承自 `HashMap`，并通过维护一个双向链表来保留插入的顺序。当你迭代 `LinkedHashMap` 中的元素时，它们将按照插入的顺序返回。



#### 常见的java集合

Java 中提供了丰富的集合框架，主要包括以下常见的集合：

1. **List（列表）：**
   - **ArrayList：** 基于动态数组实现，支持快速随机访问，适用于读取操作较多的场景。
   - **LinkedList：** 基于双向链表实现，适用于频繁的插入和删除操作。
   - **Vector：** 类似于 ArrayList，但是是线程安全的。
2. **Set（集合）：**
   - **HashSet：** 基于哈希表实现，不保证元素的顺序。
   - **LinkedHashSet：** 具有可预知迭代顺序的 HashSet。
   - **TreeSet：** 基于红黑树实现，元素按照自然顺序或者指定的顺序进行排序。
3. **Map（映射）：**
   - **HashMap：** 基于哈希表实现，提供快速的查找操作。
   - **LinkedHashMap：** 继承自 HashMap，可以保持插入顺序。
   - **TreeMap：** 基于红黑树实现，元素按照自然顺序或者指定的顺序进行排序。
4. **Queue（队列）：**
   - **LinkedList：** 可用作队列，也可用作双向队列（Deque）。
   - **PriorityQueue：** 基于优先级堆的实现，可以按照元素的优先级顺序进行检索。
5. **Deque（双端队列）：**
   - **ArrayDeque：** 由数组实现的双端队列，可以在两端进行高效插入和删除操作。
6. **Stack（栈）：**
   - **Stack：** 基于向量实现的栈，但不推荐使用，通常建议使用 Deque 代替。
7. **Collections 类：**
   - **Collections：** 提供了一系列静态方法，用于对集合进行操作，如排序、随机化、查找等。

这些集合类都位于 `java.util` 包中。在 Java 5 之后，引入了泛型（Generics），使得集合框架更加类型安全。在 Java 8 中，还引入了流式操作（Stream API），使得对集合的处理更加便捷。

选择合适的集合类取决于具体的需求和性能特征。在并发环境中，还有一系列的并发集合类，如 `ConcurrentHashMap`、`CopyOnWriteArrayList` 等，用于处理多线程场景。



#### ConcurrentHashMap为什么是线程安全的

`ConcurrentHashMap` 是线程安全的，主要基于以下设计和实现：

1. **分段锁设计：**
   - `ConcurrentHashMap` 使用了分段锁的机制。它将整个数据结构分为一定数量的段（Segment），每个段相当于一个小的 `HashMap`。每个段都有自己的锁。在大多数操作（比如 `get`、`put`）时，只需要锁住对应的段，而不是整个数据结构。
2. **读写分离：**
   - 读操作不需要锁，因此可以在没有锁的情况下并发进行。只有在写操作时，需要锁住对应的段。这样做的结果是，多个线程可以同时进行读操作，而写操作需要等待。
3. **细粒度锁：**
   - 每个段内的锁是细粒度的，这意味着不同的线程可以同时对不同的段进行写操作，提高了并发性。
4. **并发度的调整：**
   - 可以通过调整 `ConcurrentHashMap` 的并发度（即初始化时的段数）来平衡并发度和内存占用。并发度是通过 `concurrencyLevel` 参数设置的，默认值是 16。

这些设计和实现使得 `ConcurrentHashMap` 在读多写少的情况下能够提供较好的并发性能，同时避免了全局锁导致的性能瓶颈。需要注意的是，虽然 `ConcurrentHashMap` 提供了线程安全的读写操作，但对于一些复合操作（例如 `putIfAbsent`），仍然需要考虑原子性。在这种情况下，你可能需要使用额外的同步措施。

总的来说，`ConcurrentHashMap` 的设计允许多个读操作和写操作同时发生，通过分段锁和细粒度的锁来提高并发性能。



#### 为什么hashmap不是线程安全的

`HashMap` 不是线程安全的主要原因是它没有在设计上考虑多线程并发的情况。以下是导致 `HashMap` 非线程安全的一些关键点：

1. **无锁设计：**
   - `HashMap` 的设计没有使用锁机制，因此在并发写入的情况下，可能会导致多个线程同时修改数据结构，从而破坏数据的一致性。
2. **不同步的迭代器：**
   - `HashMap` 的迭代器是快速失败（fail-fast）的。如果在迭代过程中，其他线程对 `HashMap` 进行结构性的修改（增加、删除元素），就会抛出 `ConcurrentModificationException`。这是由于迭代器无法感知到其他线程的并发修改，从而可能导致不确定的行为。
3. **无法保证原子性：**
   - `HashMap` 中的操作，如 `put` 或 `remove`，不是原子性的。这意味着即使单个操作在单线程中是线程安全的，但在多线程环境中，多个操作组合起来就不是线程安全的。
4. **扩容问题：**
   - 在 `HashMap` 需要扩容时，它会重新计算哈希值，并将键值对重新分布到新的数组中。在这个过程中，如果有其他线程同时在操作，可能导致数据不一致性。

为了在多线程环境中安全地使用哈希表，Java 提供了 `ConcurrentHashMap`。`ConcurrentHashMap` 使用分段锁（Segment）来保证并发的安全性。每个段相当于一个小的 `HashMap`，在读操作时，可以并发进行，而在写操作时，只需要锁住对应的段，而不是整个数据结构。这样设计提高了并发性，避免了全局锁的瓶颈。



#### ThreadLocal的原理

`ThreadLocal` 是 Java 中用于实现线程封闭性（Thread Confinement）的工具类。它允许你创建一个存储变量的对象，每个线程都有一个该对象的副本，从而实现了在每个线程中维护独立的变量。这样的设计能够保证线程之间不会相互影响，提供了一种简单的线程安全机制。

`ThreadLocal` 的原理主要依赖于 `Thread` 类中的一个 `ThreadLocalMap` 对象，这是一个由 `ThreadLocal` 维护的键值对的映射表。在 `ThreadLocal` 中，每个线程都有自己独立的 `ThreadLocalMap` 对象。

核心原理可以总结为以下几点：

1. **每个线程都有自己的存储空间：**
   - 当调用 `ThreadLocal` 的 `set` 方法时，它会根据当前线程获取到 `ThreadLocalMap` 对象，然后将键值对存储到这个映射表中。因为每个线程都有自己的 `ThreadLocalMap` 对象，所以它们之间不会发生冲突。
2. **键是 ThreadLocal 对象，值是存储的变量：**
   - `ThreadLocalMap` 中的键是 `ThreadLocal` 对象，值是存储的变量。这样，每个线程对应一个 `ThreadLocal`，通过这个 `ThreadLocal` 就可以获取到属于该线程的变量。
3. **垃圾回收：**
   - `ThreadLocal` 中的 `ThreadLocalMap` 是通过弱引用（`WeakReference`）来实现的。这样，当线程结束时，`ThreadLocal` 对象会被垃圾回收，与之关联的变量也会被释放，防止内存泄漏。
4. **初始值：**
   - `ThreadLocal` 提供了 `initialValue` 方法，可以用于在第一次调用 `get` 方法时初始化变量的值。

下面是一个简单的示例：

```
javaCopy code
public class MyThreadLocalExample {

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // 在主线程中设置值
        threadLocal.set(1);

        // 启动两个新线程，每个线程都能看到自己的值
        new Thread(() -> {
            System.out.println("Thread 1: " + threadLocal.get()); // 输出 null，因为没有设置过
            threadLocal.set(100);
            System.out.println("Thread 1: " + threadLocal.get()); // 输出 100
        }).start();

        new Thread(() -> {
            System.out.println("Thread 2: " + threadLocal.get()); // 输出 null
            threadLocal.set(200);
            System.out.println("Thread 2: " + threadLocal.get()); // 输出 200
        }).start();

        // 主线程输出
        System.out.println("Main Thread: " + threadLocal.get()); // 输出 1
    }
}

```

在这个例子中，每个线程都有自己的 `ThreadLocalMap` 对象，它们之间互不影响。`ThreadLocal` 的实现通过弱引用和每个线程有自己的存储空间，保证了线程安全性和防止内存泄漏。



#### ThreadLocal 实例被设置为静态字段

`ThreadLocal` 实例经常被设置为静态字段的主要原因是确保在应用程序的整个生命周期内都可以访问到相同的 `ThreadLocal` 实例。静态字段属于类而不是实例，因此可以保证在不同的地方都能访问到相同的 `ThreadLocal` 实例，而不需要创建多个实例。

另外，将 `ThreadLocal` 设置为静态字段也可以方便地跨多个类或线程共享数据。通过在多个类中引用相同的 `ThreadLocal` 实例，可以在不同的类之间传递线程局部变量，而无需显式地将 `ThreadLocal` 作为参数传递。

总的来说，将 `ThreadLocal` 设置为静态字段是为了确保在整个应用程序中都能方便地访问到相同的实例，以及方便跨多个类或线程共享数据。



#### ThreadLocal实例对象会在什么情况下没有被引用

`ThreadLocal` 实例对象在以下情况下可能没有被引用：

1. 当某个线程调用 `ThreadLocal.remove()` 方法将 `ThreadLocal` 实例与当前线程的值解绑时，如果没有其他引用指向该 `ThreadLocal` 实例，那么它可能成为垃圾对象。
2. 当某个线程结束时，如果其维护的 `ThreadLocalMap` 不再被引用（例如该线程已经被终止或者线程池中的线程被回收），那么该 `ThreadLocalMap` 中的所有 `ThreadLocal` 实例可能都会成为垃圾对象。
3. 如果某个 `ThreadLocal` 实例被设置为静态字段，但在后续的程序执行过程中不再需要，那么它可能成为垃圾对象。
4. 如果 `ThreadLocal` 实例被存储在其他容器中，但在后续的程序执行过程中不再需要，并且容器本身也没有被引用，那么它可能成为垃圾对象。

在这些情况下，如果没有其他引用指向 `ThreadLocal` 实例，那么它将被Java垃圾回收器回收，并释放相关资源。



#### ThreadLocal的应用

ThreadLocal 是 Java 中的一个类，它提供了线程局部变量的功能。ThreadLocal 实例通常被声明为静态变量，每个线程都可以访问和修改自己的副本，而不会影响其他线程的副本。以下是 ThreadLocal 的一个常见应用之一：

1. **线程安全的数据库连接管理：**在多线程环境下，如果多个线程需要访问数据库，通常会创建数据库连接池来管理数据库连接。而使用 ThreadLocal 可以简化数据库连接的管理过程，确保每个线程都拥有自己的数据库连接，从而避免线程之间的竞争和资源浪费。

   ```
   javaCopy code
   public class DatabaseConnectionManager {
       private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

       public static Connection getConnection() throws SQLException {
           Connection connection = connectionHolder.get();
           if (connection == null) {
               connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "username", "password");
               connectionHolder.set(connection);
           }
           return connection;
       }

       public static void releaseConnection() throws SQLException {
           Connection connection = connectionHolder.get();
           if (connection != null) {
               connection.close();
               connectionHolder.remove(); // 清理 ThreadLocal 中的连接
           }
       }
   }

   ```

   在上面的示例中，`connectionHolder` 是一个 ThreadLocal 变量，用于存储每个线程的数据库连接。`getConnection` 方法首先尝试从 ThreadLocal 中获取连接，如果不存在则创建一个新的连接，并将其存储到 ThreadLocal 中。`releaseConnection` 方法用于释放连接，并清理 ThreadLocal 中的连接。这样，每个线程都可以安全地访问自己的数据库连接，而不会受到其他线程的影响。

   在使用 ThreadLocal 进行线程安全的数据库连接管理时，需要注意正确地释放连接，避免资源泄漏。





另一个常见的 ThreadLocal 应用是在 Web 应用中管理用户会话（session）信息。在 Web 开发中，每个用户都有自己的会话数据，通常存储在会话对象中，并且在整个用户请求的生命周期内都可以访问。

以下是一个简单的示例，演示了如何使用 ThreadLocal 来管理用户会话信息：

```
javaCopy code
public class UserSessionManager {
    private static ThreadLocal<UserSession> userSessionThreadLocal = new ThreadLocal<>();

    public static void setUserSession(UserSession userSession) {
        userSessionThreadLocal.set(userSession);
    }

    public static UserSession getUserSession() {
        return userSessionThreadLocal.get();
    }

    public static void clearUserSession() {
        userSessionThreadLocal.remove();
    }
}

```

在这个示例中，`UserSessionManager` 类用于管理用户会话信息。`userSessionThreadLocal` 是一个 ThreadLocal 变量，用于存储每个线程的用户会话对象。

当用户登录时，可以将用户会话对象存储到 ThreadLocal 中：

```
javaCopy code
// 用户登录时创建会话对象，并存储到 ThreadLocal 中
UserSession userSession = new UserSession(userId, username);
UserSessionManager.setUserSession(userSession);

```

在用户的每个请求处理过程中，可以通过 `UserSessionManager.getUserSession()` 方法获取当前线程的用户会话对象，并对其进行操作：

```
javaCopy code
// 获取当前线程的用户会话对象
UserSession userSession = UserSessionManager.getUserSession();
// 进行会话相关操作，如设置属性、检查权限等

```

当用户注销或会话结束时，需要清理 ThreadLocal 中的会话对象：

```
javaCopy code
// 用户注销或会话结束时清理 ThreadLocal 中的会话对象
UserSessionManager.clearUserSession();

```

通过 ThreadLocal 管理用户会话信息，可以确保每个用户的会话数据在整个请求处理过程中都是线程安全的，而不会被其他用户的请求所干扰。

总结:

三大场景:

存储用户Session

数据库连接，处理数据库事务

数据跨层传递（controller,service, dao）

Spring使用ThreadLocal解决线程安全问题



#### 比较float大小

在 Java 中，比较两个 float 类型的数值大小可以使用以下方法：

1. **使用比较运算符（<、<=、==、>=、>）：** 可以直接使用比较运算符对两个 float 数值进行比较。然而，由于浮点数的表示精度有限，因此直接使用等号（==）进行比较可能会遇到精度问题。建议使用其他比较运算符来避免这种情况。

   ```
   javaCopy code
   float a = 3.14f;
   float b = 2.71f;

   if (a < b) {
       System.out.println("a 小于 b");
   } else if (a > b) {
       System.out.println("a 大于 b");
   } else {
       System.out.println("a 等于 b");
   }

   ```

2. **使用 Float.compare() 方法：** 可以使用 Float 类的 compare() 静态方法来比较两个 float 数值的大小。该方法返回一个整数，表示两个数的比较结果，其中正数表示第一个数大于第二个数，负数表示第一个数小于第二个数，零表示两个数相等。

   ```
   javaCopy code
   float a = 3.14f;
   float b = 2.71f;

   int result = Float.compare(a, b);
   if (result < 0) {
       System.out.println("a 小于 b");
   } else if (result > 0) {
       System.out.println("a 大于 b");
   } else {
       System.out.println("a 等于 b");
   }

   ```

无论使用哪种方法，都需要注意浮点数比较时可能遇到的精度问题。建议使用合适的比较方法，并根据实际需求处理精度问题。



#### 内存泄露和内存溢出

内存泄漏（Memory Leak）和内存溢出（Memory Overflow）都是与内存管理相关的问题，但它们的原因和表现方式不同。

1. **内存泄漏（Memory Leak）**：
   - 内存泄漏是指程序中申请的内存在不再需要时没有被释放，导致内存空间无法被再次使用。常见的内存泄漏情况包括未及时释放对象的引用、未关闭的资源（如文件、数据库连接等）、循环引用等。
   - 内存泄漏会导致程序长时间运行后占用的内存不断增加，最终耗尽可用内存，导致程序性能下降甚至崩溃。
2. **内存溢出（Memory Overflow）**：
   - 内存溢出是指程序申请的内存超出了系统的可用内存范围，无法继续申请更多的内存空间。常见的内存溢出情况包括栈溢出和堆溢出。
   - 栈溢出（Stack Overflow）发生在栈空间不足以容纳函数调用的情况下，导致函数调用的递归层次过深，栈空间被耗尽。
   - 堆溢出（Heap Overflow）发生在堆内存申请过多对象导致无法分配更多内存空间的情况下，常见的原因包括创建大量对象、对象无法被垃圾回收等。

总的来说，内存泄漏是因为资源没有被释放导致内存持续占用，而内存溢出是因为申请的内存超出了系统的可用内存范围。在编程中，应该注意及时释放不再使用的资源，避免内存泄漏；同时，合理管理内存使用，避免过多的内存申请，防止内存溢出。



#### String的储存位置

在JDK 1.8及之后的版本中，字符串常量一般存储在堆（Heap）中的字符串常量池（String Pool）中。在JDK 1.7及之前的版本中，字符串常量池存储在永久代（Permanent Generation）中，但是在JDK 1.8中，随着永久代的移除，字符串常量池被移到了堆中，这样可以更好地管理字符串常量，避免永久代的内存泄漏问题。



#### JMM内存模型

JMM（Java Memory Model）是Java内存模型的缩写，是一种抽象的概念，描述了Java程序中多线程之间的内存访问行为规则。JMM定义了Java虚拟机如何在主存（Main Memory）和工作内存（Working Memory）之间进行数据交互，以及如何保证多线程之间的内存可见性、有序性和原子性。

具体来说，JMM主要包括以下几个方面的规定：

1. **原子性（Atomicity）**：JMM保证了简单的读取和写入操作的原子性。对于单个的读取和写入操作，JMM保证了它们的原子性，但是对于复合操作，比如递增操作++，JMM不能保证其原子性。
2. **可见性（Visibility）**：当一个线程修改了共享变量的值之后，其他线程能够立即看到修改后的值。为了保证可见性，JMM规定，线程在读取变量的值时，必须从主存中读取，而不是从自己的工作内存中读取。
3. **有序性（Ordering）**：JMM保证了程序执行的顺序性。具体来说，JMM规定了编译器和处理器对指令进行重排序的规则，以及如何防止重排序导致的程序执行结果异常。
4. **happens-before关系**：JMM引入了happens-before关系来描述线程间操作的先后顺序。happens-before关系是JMM中一个重要的概念，它用来指定某个操作必须在另一个操作之前执行，从而保证多线程程序的正确性。

总的来说，JMM定义了Java程序中多线程间内存访问的规则和约束，保证了多线程程序的正确性和一致性。开发人员在编写多线程程序时，需要遵守JMM的规定，合理地使用同步机制和内存屏障等手段，以确保程序的正确性。



#### 类的加载过程

在Java中，类的加载过程经历以下几个阶段：

1. **加载（Loading）**：加载是类加载过程的第一个阶段。在加载阶段，类的字节码被加载到内存中，并被转化为方法区（在Java 7之前，叫作永久代，Java 8以后被称为元空间）内的类对象。这个阶段包括查找和读取类的字节码文件。
2. **验证（Verification）**：在验证阶段，Java虚拟机会对加载的类进行验证，以确保字节码符合Java语言规范，没有安全漏洞。验证包括类型检查、字节码验证、符号引用验证等。
3. **准备（Preparation）**：在准备阶段，Java虚拟机为类的静态变量分配内存，并初始化这些变量为默认值。这些变量存储在方法区中。
4. **解析（Resolution）**：在解析阶段，虚拟机将类、接口、字段和方法的符号引用解析为直接引用。这个阶段并不是必需的，有些虚拟机可能会在运行时再进行解析。
5. **初始化（Initialization）**：在初始化阶段，Java虚拟机对类进行初始化。这个阶段是类加载过程中的最后一个阶段。在初始化阶段，虚拟机会执行类构造器 `<clinit>` 方法，这是类中静态变量的赋值和静态块的执行的地方。初始化过程是按需进行的，只有在首次主动使用类的时候才会触发初始化。

需要注意的是，初始化是类加载的阶段之一，但只有在类首次主动使用时才会触发初始化。类的加载和初始化是懒加载的过程，只有当程序中引用了该类时，才会进行加载和初始化操作。这有助于减少不必要的资源浪费。

这个类加载过程确保了Java程序在运行时能够按需加载并初始化需要的类，从而保证了类加载的高效性和延迟性。



#### tryLock()和lock()

在Java中，`Lock` 接口提供了比传统 `synchronized` 关键字更灵活的锁定机制。`Lock` 接口的实现（如 `ReentrantLock`）主要提供了两种锁定机制：`lock` 和 `tryLock`。

lock:

- `lock` 方法用于获取锁。如果锁已被其他线程持有，则当前线程将等待直到锁被释放，然后再获得锁。
- 使用 `lock` 方法时，如果锁已被其他线程占用，当前线程将被阻塞，直到锁被释放。
- `lock` 方法不会返回任何值，调用者无法得知锁的获取是否成功，除非成功获取到锁。
- 在使用 `lock` 方法时，通常需要在 `try` 块中获取锁，并在相应的 `finally` 块中释放锁，以确保锁最终被释放，避免死锁。

tryLock:

- `tryLock` 方法尝试获取锁，如果锁立即可用（即当前没有其他线程持有锁），则获取锁并立即返回 `true`；如果锁不可用，则立即返回 `false`，不会等待。
- `tryLock` 方法还有另一个重载版本，允许线程等待锁一定的时间。通过指定的等待时间，它尝试在这段时间内获取锁，如果在指定时间内锁变得可用且当前线程获取了锁，则返回 `true`；如果指定时间内没有获取到锁，则返回 `false`。
- `tryLock` 的这种非阻塞特性使得它可以用于需要立即响应的场景，不会让线程长时间挂起等待锁。

使用场景

- 当不希望线程在锁不可用时无限期等待，或者希望在尝试获取锁失败时立即做出响应（例如，尝试获取另一种资源或快速失败）时，`tryLock` 很有用。
- `lock` 方法更适合于需要确保当前线程不继续执行，直到成功获取锁的场景。

示例

```
javaCopy code
Lock lock = new ReentrantLock();

// 使用lock
lock.lock();
try {
    // 处理业务逻辑
} finally {
    lock.unlock();
}

// 使用tryLock
if(lock.tryLock()) {
    try {
        // 处理业务逻辑
    } finally {
        lock.unlock();
    }
} else {
    // 如果不能立即获得锁，则可以执行其他操作
}

```

总之，`tryLock` 提供了更灵活的锁管理策略，允许线程决定**在不能立即获取锁时应如何继续**。而 `lock` 提供了一种保证会获取锁（即使需要等待）的方式，适用于需要确保执行特定区域代码的场景。



#### 默认的4个线程池

在 Java 中，`java.util.concurrent.Executors` 类提供了一些工厂方法用于创建各种类型的线程池。以下是一些常见的默认线程池：

1. **newFixedThreadPool(int nThreads)**：创建一个固定大小的线程池，该线程池中的线程数量固定为指定的数量。任务提交到这个线程池后，会被分配给其中的线程执行。如果所有线程都在忙碌，新任务将会被放入一个队列中，直到有空闲的线程可用。使用了无界队列：`LinkedBlockingQueue`。当向 `FixedThreadPool` 提交的任务多于核心线程数时，这些任务会被放入无界队列中等待执行。由于队列是无界的，因此不会拒绝任务，但是需要注意可能会造成内存资源的占用。
2. **newCachedThreadPool()**：创建一个可缓存的线程池，该线程池中的线程数量不固定，可以根据任务的数量动态地增加或减少线程的数量。当有任务提交到这个线程池时，如果有空闲的线程可用，则会重用现有线程；如果所有线程都在忙碌，则会创建新的线程。空闲线程在长时间不使用时会被回收。使用了同步队列：`SynchronousQueue`。`CachedThreadPool` 不会保留线程，当有新任务到达时会尝试复用之前创建的线程，如果没有可用的线程，则会创建新线程。由于 `SynchronousQueue` 是无容量的，因此每个任务都会导致创建一个新的线程。
3. **newSingleThreadExecutor()**：创建一个单线程的线程池，该线程池中只有一个线程。所有提交到这个线程池的任务都会按顺序执行，即使有多个线程调用了 `execute()` 方法也不会并发执行。使用了无界队列：`LinkedBlockingQueue`。`SingleThreadExecutor` 使用单个工作线程来顺序执行任务，因此所有提交的任务都会被放入无界队列中等待执行。
4. **newScheduledThreadPool(int corePoolSize)**：创建一个定时执行任务的线程池。该线程池可以根据需要执行延迟任务或周期性任务，可用于定时执行任务、定时任务调度等场景。使用了延时队列：`DelayedWorkQueue`。`ScheduledThreadPoolExecutor` 可以延时执行任务，它使用的队列是一个特殊的延时队列，它实际上是 `PriorityBlockingQueue` 的一个变种，用于按照任务的延时时间进行排序。

这些是 Java 中一些常见的默认线程池，通过 `Executors` 类的工厂方法可以方便地创建这些线程池实例。在实际应用中，可以根据具体需求选择合适的线程池来处理任务。



