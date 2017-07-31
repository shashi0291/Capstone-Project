DROP TABLE IF EXISTS "category";
CREATE TABLE "category" ("id" INTEGER PRIMARY KEY  NOT NULL , "name" VARCHAR NOT NULL , "description" TEXT NOT NULL );
INSERT INTO "category" VALUES(1,'Creational','Creational patterns concern the process of object creation.');
INSERT INTO "category" VALUES(2,'Structural','Structural patterns deal with the composition of classes or objects.');
INSERT INTO "category" VALUES(3,'Behavioral','Behavioral patterns characterize the ways in which classes or objects interact and distribute responsibility.');
DROP TABLE IF EXISTS "favorite_pattern";
CREATE TABLE "favorite_pattern" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "name" TEXT NOT NULL , "intent" TEXT NOT NULL , "description" TEXT NOT NULL , "imageName" VARCHAR, "categoryId" INTEGER NOT NULL );
DROP TABLE IF EXISTS "pattern";
CREATE TABLE "pattern" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "name" TEXT NOT NULL , "intent" TEXT NOT NULL , "description" TEXT NOT NULL , "imageName" VARCHAR, "categoryId" INTEGER NOT NULL );
INSERT INTO "pattern" VALUES(1,'Singleton','Ensure a class only has one instance, and provide a global point of access to it.','<h2>Motivation</h2>
<p>Its important for some classes to have exactly one instance. Although there can be many printers in a system, there should be only one printer spooler. There should be only one file system and one window manager. A digital filter will have one A/D converter. An accounting system will be dedicated to serving one company.</p>
<p>How do we ensure that a class has only one instance and that the instance is easily accessible? A global variable makes an object accessible, but it doesnot keep you from instantiating multiple objects.</p>
<p>A better solution is to make the class itself responsible for keeping track of its sole instance. The class can ensure that no other instance can be created (by intercepting requests to create new objects), and it can provide a way to access the instance. This is the Singleton pattern.</p>
<h2>Applicability</h2>
<p>Use the Singleton pattern when</p>
<ul>
<li>there must be exactly one instance of a class, and it must be accessible to clients from a well-known access point.</li>
<li>when the sole instance should be extensible by subclassing, and clients should be able to use an extended instance without modifying their code.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>Clients access a Singleton instance solely through Singletons Instance operation. </li>
</ul>
<h2>Consequences</h2>
<p>The Singleton pattern has several benefits:</p>
<ol>
<li>Controlled access to sole instance. Because the Singleton class encapsulates its sole instance, it can have strict control over how and when clients access it.</li>
<li>Reduced name space. The Singleton pattern is an improvement over global variables. It avoids polluting the name space with global variables that store sole instances.</li>
<li>Permits refinement of operations and representation. The Singleton class may be subclassed, and its easy to configure an application with an instance of this extended class. You can configure the application with an instance of the class you need at run-time.</li>
<li>Permits a variable number of instances. The pattern makes it easy to change your mind and allow more than one instance of the Singleton class. Moreover, you can use the same approach to control the number of instances that the Design Patterns: Elements of Reusable Object-Oriented Software 146 application uses. Only the operation that grants access to the Singleton instance needs to change.</li>
<li>More flexible than class operations. Another way to package a singletons functionality is to use class operations (that is, static member functions in C++ or class methods in Smalltalk). But both of these language techniques make it hard to change a design to allow more than one instance of a class. Moreover, static member functions in C++ are never virtual, so subclasses cannot override them polymorphically.</li>
</ol>','icon_singleton',1);
INSERT INTO "pattern" VALUES(2,'Abstract Factory','Provide an interface for creating families of related or dependent objects without specifying their concrete classes.','<h2>Motivation</h2>
<p>Consider a user interface toolkit that supports multiple look-and-feel standards, such as Motif and Presentation Manager. Different look-and-feels define different appearances and behaviors for user interface "widgets" like scroll bars, windows, and buttons. To be portable across look-and-feel standards, an application should not hard-code its widgets for a particular look and feel. Instantiating look-and-feel-specific classes of widgets throughout the application makes it hard to change the look and feel later.</p>
<p>We can solve this problem by defining an abstract WidgetFactory class that declares an interface for creating each basic kind of widget. Theres also an abstract class for each kind of widget, and concrete subclasses implement widgets for specific look-and-feel standards. WidgetFactorys interface has an operation that returns a new widget object for each abstract widget class. Clients call these operations to obtain widget instances, but clients arent aware of the concrete classes theyre using. Thus clients stay independent of the prevailing look and feel.</p>
<p>There is a concrete subclass of WidgetFactory for each look-and-feel standard. Each subclass implements the operations to create the appropriate widget for the look and feel. For example, the CreateScrollBar operation on the MotifWidgetFactory instantiates and returns a Motif scroll bar, while the corresponding operation on the PMWidgetFactory returns a scroll bar for Presentation Manager. Clients create widgets solely through the WidgetFactory interface and have no knowledge of the classes that implement widgets for a particular look and feel. In other words, clients only have to commit to an interface defined by an abstract class, not a particular concrete class.</p>
<p>A WidgetFactory also enforces dependencies between the concrete widget classes. A Motif scroll bar should be used with a Motif button and a Motif text editor, and that constraint is enforced automatically as a consequence of using a MotifWidgetFactory.</p>
<p>There is a concrete subclass of WidgetFactory for each look-and-feel standard. Each subclass implements the operations to create the appropriate widget for the look and feel. For example, the CreateScrollBar operation on the MotifWidgetFactory instantiates and returns a Motif scroll bar, while the corresponding operation on the PMWidgetFactory returns a scroll bar for Presentation Manager. Clients create widgets solely through the WidgetFactory interface and have no knowledge of the classes that implement widgets for a particular look and feel. In other words, clients only have to commit to an interface defined by an abstract class, not a particular concrete class.</p>
<p>A WidgetFactory also enforces dependencies between the concrete widget classes. A Motif scroll bar should be used with a Motif button and a Motif text editor, and that constraint is enforced automatically as a consequence of using a MotifWidgetFactory.</p>
<h2>Applicability</h2>
<p>Use the Abstract Factory pattern when</p>
<ul>
<li>a system should be independent of how its products are created, composed,<br />and represented.</li>
<li>a system should be configured with one of multiple families of products.</li>
<li>a family of related product objects is designed to be used together, and you need to enforce this constraint.</li>
<li>you want to provide a class library of products, and you want to reveal<br />just their interfaces, not their implementations.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>Normally a single instance of a ConcreteFactory class is created at run-time.<br />This concrete factory creates product objects having a particular<br />implementation. To create different product objects, clients should use<br />a different concrete factory.</li>
<li>AbstractFactory defers creation of product objects to its ConcreteFactory<br />subclass.</li>
</ul>
<h2>Consequences</h2>
<p>The Abstract Factory pattern has the following benefits and liabilities:</p>
<ol>
<li>It isolates concrete classes. The Abstract Factory pattern helps you control the classes of objects that an application creates. Because a factory encapsulates the responsibility and the process of creating product objects, it isolates clients from implementation classes. Clients manipulate instances through their abstract interfaces. Product class names are isolated in the implementation of the concrete factory they do not appear in client code.</li>
<li>It makes exchanging product families easy. The class of a concrete factory appears only once in an application that is, where its instantiated. This makes it easy to change the concrete factory an application uses. It can use different product configurations simply by changing the concrete factory. Because an abstract factory creates a complete family of products, the whole product family changes at once. In our user interface example, we can switch from Motif widgets to Presentation Manager widgets simply<br />by switching the corresponding factory objects and recreating the interface.</li>
<li>It promotes consistency among products. When product objects in a family are designed to work together, its important that an application use objects from only one family at a time. AbstractFactory makes this easy to enforce.</li>
<li>Supporting new kinds of products is difficult. Extending abstract factories to produce new kinds of Products isnt easy. Thats because the<br />AbstractFactory interface fixes the set of products that can be created. Supporting new kinds of products requires extending the factory interface, which involves changing the AbstractFactory class and all of its subclasses. We discuss one solution to this problem in the Implementation section.</li>
</ol>','icon_abstract_factory',1);
INSERT INTO "pattern" VALUES(3,'Builder','Separate the construction of a complex object from its representation so that the same construction process can create different representations.','<h2>Motivation</h2>
<p>A reader for the RTF (Rich Text Format) document exchange format should be able to convert RTF to many text formats. The reader might convert RTF documents into plain ASCII text or into a text widget that can be edited interactively. The problem, however, is that the number of possible conversions is open-ended. So it should be easy to add a new conversion without modifying the reader.</p>
<p>A solution is to configure the RTFReader class with a TextConverter object that converts RTF to another textual representation. As the RTFReader parses the RTF document, it uses the TextConverter to perform the conversion. Whenever the RTFReader recognizes an RTF token (either plain text or an RTF control word), it issues a request to the TextConverter to convert the token. TextConverter objects are responsible both for performing the data conversion and for representing the token in a particular format.</p>
<p>Subclasses of TextConverter specialize in different conversions and formats. For example, an ASCIIConverter ignores requests to convert anything except plain text. A TeXConverter, on the other hand, will implement operations for all requests in order to produce a TeX representation that captures all the stylistic information in the text. A TextWidgetConverter will produce a complex user interface object that lets the user see and edit the text.</p>
<p>Each kind of converter class takes the mechanism for creating and assembling a complex object and puts it behind an abstract interface. The converter is separate from the reader, which is responsible for parsing an RTF document.</p>
<p>The Builder pattern captures all these relationships. Each converter class is called a builder in the pattern, and the reader is called the director. Applied to this example, the Builder pattern separates the algorithm for interpreting a textual format (that is, the parser for RTF documents) from how a converted format gets created and represented. This lets us reuse the RTFReaders parsing algorithm to create different text representations from RTF documents just configure the RTFReader with different subclasses of TextConverter.</p>
<h2>Applicability</h2>
<p>Use the Builder pattern when</p>
<ul>
<li>the algorithm for creating a complex object should be independent of the parts that make up the object and how theyre assembled.</li>
<li>the construction process must allow different representations for the object thats constructed.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>The client creates the Director object and configures it with the desired Builder object.</li>
<li>Director notifies the builder whenever a part of the product should be built.</li>
<li>Builder handles requests from the director and adds parts to the product.</li>
<li>The client retrieves the product from the builder.</li>
</ul>
<h2>Consequences</h2>
<p>Here are key consequences of the Builder pattern:</p>
<ol>
<li>It lets you vary a products internal representation. The Builder object provides the director with an abstract interface for constructing the<br />product. The interface lets the builder hide the representation and internal structure of the product. It also hides how the product gets assembled. Because the product is constructed through an abstract interface, all you have to do to change the products internal representation is define a new kind of builder.</li>
<li>It isolates code for construction and representation. The Builder pattern improves modularity by encapsulating the way a complex object is constructed and represented. Clients neednt know anything about the classes that define the products internal structure such classes donot appear in Builders interface. Each ConcreteBuilder contains all the code to create and assemble a particular kind of product. The code is written once then different Directors can reuse it to build Product variants from the same set of parts. In the earlier RTF example, we could define a reader for a format other than RTF, say, an SGMLReader, and use the same TextConverters to generate ASCIIText, TeXText, and TextWidget renditions of SGML documents.</li>
<li>It gives you finer control over the construction process. Unlike creational patterns that construct products in one shot, the Builder pattern constructs the product step by step under the directors control. Only when the product is finished does the director retrieve it from the builder. Hence the Builder interface reflects the process of constructing the product more than other creational patterns. This gives you finer control over the construction process and consequently the internal structure of the resulting product.</li>
</ol>','icon_builder',1);
INSERT INTO "pattern" VALUES(4,'Factory Method','Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.','<h2>Motivation</h2>
<p>Frameworks use abstract classes to define and maintain relationships between objects. A framework is often responsible for creating these objects as well.</p>
<p>Consider a framework for applications that can present multiple documents to the user. Two key abstractions in this framework are the classes Application and Document. Both classes are abstract, and clients have to subclass them to realize their application-specific implementations. To create a drawing application, for example, we define the classes DrawingApplication and DrawingDocument. The Application class is responsible for managing Documents and will create them as required when the user selects Open or New from a menu, for example.</p>
<p>Because the particular Document subclass to instantiate is application-specific, the Application class canot predict the subclass of Document to instantiate the Application class only knows when a new document should be created, not what kind of Document to create. This creates a dilemma: The framework must instantiate classes, but it only knows about abstract classes, which it cannot instantiate.</p>
<p>The Factory Method pattern offers a solution. It encapsulates the knowledge of which Document subclass to create and moves this knowledge out of the framework.Application subclasses redefine an abstract CreateDocument operation on Application to return the appropriate Document subclass. Once an Application subclass is instantiated, it can then instantiate application-specific Documents without knowing their class. We call CreateDocument a factory method because its responsible for manufacturing an object.</p>
<h2>Applicability</h2>
<p>Use the Factory Method pattern when</p>
<ul>
<li>a class cannot anticipate the class of objects it must create.</li>
<li>a class wants its subclasses to specify the objects it creates.</li>
<li>classes delegate responsibility to one of several helper subclasses, and you want to localize the knowledge of which helper subclass is the delegate.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>Creator relies on its subclasses to define the factory method so that it returns an instance of the appropriate ConcreteProduct.</li>
</ul>
<h2>Consequences</h2>
<p>Factory methods eliminate the need to bind application-specific classes into your code. The code only deals with the Product interface therefore it can work with any user-defined ConcreteProduct classes.</p>
<p>A potential disadvantage of factory methods is that clients might have to subclass the Creator class just to create a particular ConcreteProduct object. Subclassing is fine when the client has to subclass the Creator class anyway, but otherwise the client now must deal with another point of evolution.</p>
<p>Here are two additional consequences of the Factory Method pattern:</p>
<ol>
<li>Provides hooks for subclasses. Creating objects inside a class with a factory method is always more flexible than creating an object directly. Factory Method gives subclasses a hook for providing an extended version of an object. In the Document example, the Document class could define a factory method called CreateFileDialog that creates a default file dialog object for opening an existing document. A Document subclass can define an application-specific file dialog by overriding this factory method. In this case the factory method is not abstract but provides a reasonable default implementation.</li>
<li>Connects parallel class hierarchies. In the examples we have considered so far, the factory method is only called by Creators. But this doesnot have to be the case clients can find factory methods useful, especially in the case of parallel class hierarchies.<br />Parallel class hierarchies result when a class delegates some of its responsibilities to a separate class. Consider graphical figures that can<br />be manipulated interactively that is, they can be stretched, moved, or rotated using the mouse. Implementing such interactions isn t always easy. It often requires storing and updating information that records the state of the manipulation at a given time. This state is needed only during manipulation therefore it neednot be kept in the figure object. Moreover, different figures behave differently when the user manipulates them. For example, stretching a line figure might have the effect of moving an endpoint, whereas stretching a text figure may change its line spacing.</li>
</ol>','icon_factory_method',1);
INSERT INTO "pattern" VALUES(5,'Prototype','Specify the kinds of objects to create using a prototypical instance, and create new objects by copying this prototype.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_prototype',1);
INSERT INTO "pattern" VALUES(6,'Adapter','Convert the interface of a class into another interface clients expect. Adapter lets classes work together that couldn''t otherwise because of incompatible interfaces.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_adapter',2);
INSERT INTO "pattern" VALUES(7,'Bridge','Decouple an abstraction from its implementation so that the two canvary independently.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_bridge',2);
INSERT INTO "pattern" VALUES(8,'Composite','Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_composite',2);
INSERT INTO "pattern" VALUES(9,'Decorator','Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_decorator',2);
INSERT INTO "pattern" VALUES(10,'Facade','Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_facade',2);
INSERT INTO "pattern" VALUES(11,'Flyweight','Use sharing to support large numbers of fine-grained objects efficiently.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_flyweight',2);
INSERT INTO "pattern" VALUES(12,'Proxy','Provide a surrogate or placeholder for another object to control access to it.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_proxy',2);
INSERT INTO "pattern" VALUES(13,'Chain of Responsibility','Avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request. Chain the receiving objects and pass the request along the chain until an object handles it.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_chain_of_responsibility',3);
INSERT INTO "pattern" VALUES(14,'Command','Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_command',3);
INSERT INTO "pattern" VALUES(15,'Interpreter','Given a language, define a represention for its grammar along with an interpreter that uses the representation to interpret sentences in the language.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_interpreter',3);
INSERT INTO "pattern" VALUES(16,'Iterator','Provide a way to access the elements of an aggregate object sequentially without exposing its underlying representation.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_iterator',3);
INSERT INTO "pattern" VALUES(17,'Mediator','Define an object that encapsulates how a set of objects interact. Mediator promotes loose coupling by keeping objects from referring to each other explicitly, and it lets you vary their interaction independently.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_mediator',3);
INSERT INTO "pattern" VALUES(18,'Memento','Without violating encapsulation, capture and externalize an object''s internal state so that the object can be restored to this state later.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_memento',3);
INSERT INTO "pattern" VALUES(19,'Observer','Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_observer',3);
INSERT INTO "pattern" VALUES(20,'State','Allow an object to alter its behavior when its internal state changes. The object will appear to change its class. ','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_state',3);
INSERT INTO "pattern" VALUES(21,'Strategy','Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_strategy',3);
INSERT INTO "pattern" VALUES(22,'Template Method','Define the skeleton of an algorithm in an operation, deferring some steps to subclasses. Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm''s structure.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_template_method',3);
INSERT INTO "pattern" VALUES(23,'Visitor','Represent an operation to be performed on the elements of an object structure. Visitor lets you define a new operation without changing the classes of the elements on which it operates.','<h2>Motivation</h2>
<p>You could build an editor for music scores by customizing a general framework for graphical editors and adding new objects that represent notes, rests, and staves. The editor framework may have a palette of tools for adding these music objects to the score. The palette would also include tools for selecting, moving, and otherwise manipulating music objects. Users will click on the quarter-note tool and use it to add quarter notes to the score. Or they can use the move tool to move a note up or down on the staff, thereby changing its pitch.</p>
<p>Let us assume the framework provides an abstract Graphic class for graphical components, like notes and staves. Moreover, it will provide an abstract Tool class for defining tools like those in the palette. The framework also predefines a GraphicTool subclass for tools that create instances of graphical objects and add them to the document.</p>
<p>But GraphicTool presents a problem to the framework designer. The classes for notes and staves are specific to our application, but the GraphicTool class belongs to the framework. GraphicTool doesnot know how to create instances of our music classes to add to the score. We could subclass GraphicTool for each kind of music object, but that would produce lots of subclasses that differ only in the kind of music object they instantiate. We know object composition is a flexible alternative to subclassing. The question is, how can the framework use it to parameterize instances of GraphicTool by the class of Graphic they are supposed to create?</p>
<p>The solution lies in making GraphicTool create a new Graphic by copying or "cloning" an instance of a Graphic subclass. We call this instance a prototype. GraphicTool is parameterized by the prototype it should clone and add to the document. If all Graphic subclasses support a Clone operation, then the GraphicTool can clone any kind of Graphic.</p>
<p>So in our music editor, each tool for creating a music object is an instance of GraphicTool thats initialized with a different prototype. Each GraphicTool instance will produce a music object by cloning its prototype and adding the clone to the score.</p>
<p>We can use the Prototype pattern to reduce the number of classes even further. We have separate classes for whole notes and half notes, but thats probably unnecessary. Instead they could be instances of the same class initialized with different bitmaps and durations. A tool for creating whole notes becomes just a GraphicTool whose prototype is a MusicalNote initialized to be a whole note. This can reduce the number of classes in the system dramatically. It also makes it easier to add a new kind of note to the music editor.</p>
<h2>Applicability</h2>
<p>Use the Prototype pattern when a system should be independent of how its products are created, composed, and represented  and</p>
<ul>
<li>when the classes to instantiate are specified at run-time, for example, by dynamic loading  or</li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products  or</li>
<li>when instances of a class can have one of only a few different combinations of state. It may be more convenient to install a corresponding number of prototypes and clone them rather than instantiating the class manually, each time with the appropriate state.</li>
</ul>
<h2>Collaborations</h2>
<ul>
<li>A client asks a prototype to clone itself.</li>
</ul>
<h2>Consequences</h2>
<p>Prototype has many of the same consequences that Abstract Factory (99) and Builder (110) have: It hides the concrete product classes from the client, thereby reducing the number of names clients know about. Moreover, these patterns let a client work with application-specific classes without modification.</p>
<p>Additional benefits of the Prototype pattern are listed below.</p>
<ol>
<li>Adding and removing products at run-time. Prototypes let you incorporate a new concrete product class into a system simply by registering a prototypical instance with the client. Thats a bit more flexible than other creational patterns, because a client can install and remove prototypes at run-time.</li>
<li>Specifying new objects by varying values. Highly dynamic systems let you define new behavior through object composition by specifying values for an objects variables, for example and not by defining new classes. You effectively define new kinds of objects by instantiating existing classes and registering the instances as prototypes of client objects. A client can exhibit new behavior by delegating responsibility to the prototype.</li>
</ol>
<p>The main liability of the Prototype pattern is that each subclass of Prototype must implement the Clone operation, which may be difficult. For example, adding Clone is difficult when the classes under consideration already exist. Implementing Clone can be difficult when their internals include objects that donot support copying or have circular references.</p>','icon_visitor',3);
DROP TABLE IF EXISTS "recent_pattern";
CREATE TABLE "recent_pattern" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "name" TEXT NOT NULL , "intent" TEXT NOT NULL , "description" TEXT NOT NULL , "imageName" VARCHAR, "categoryId" INTEGER NOT NULL );
