BEGIN TRANSACTION;
CREATE TABLE "category_list" (
	`CategoryId`	INTEGER NOT NULL UNIQUE,
	`DisplayTitle`	TEXT,
	`CategoryName`	TEXT NOT NULL UNIQUE,
	`Description`	TEXT NOT NULL
);
INSERT INTO `category_list` VALUES (1,'Creational','Creational patterns','These design patterns provide a way to create objects while hiding the creation logic, rather than instantiating objects directly using new operator. This gives program more flexibility in deciding which objects need to be created for a given use case.');
INSERT INTO `category_list` VALUES (2,'Structural','Structural Patterns','These design patterns concern class and object composition. Concept of inheritance is used to compose interfaces and define ways to compose objects to obtain new functionalities.');
INSERT INTO `category_list` VALUES (3,'Behavioral','Behavioral Patterns
','These design patterns are specifically concerned with communication between objects.');
CREATE TABLE "category_details" (
	`CategoryId`	INTEGER NOT NULL,
	`CategoryName`	TEXT NOT NULL,
	`DisplayTitle`	TEXT,
	`Description`	TEXT NOT NULL,
	`Implementation`	TEXT NOT NULL,
	`ImageName`	TEXT NOT NULL
);
INSERT INTO `category_details` VALUES (1,'Creational','Factory Pattern','Factory pattern is one of the most used design patterns in Java. This type of design pattern comes under creational pattern as this pattern provides one of the best ways to create an object.

In Factory pattern, we create object without exposing the creation logic to the client and refer to newly created object using a common interface.','We''re going to create a Shape interface and concrete classes implementing the Shape interface. A factory class ShapeFactory is defined as a next step.

FactoryPatternDemo, our demo class will use ShapeFactory to get a Shape object. It will pass information (CIRCLE / RECTANGLE / SQUARE) to ShapeFactory to get the type of object it needs.','icon_factory_pattern');
INSERT INTO `category_details` VALUES (1,'Creational','Abstract Factory Pattern','Abstract Factory patterns work around a super-factory which creates other factories. This factory is also called as factory of factories. This type of design pattern comes under creational pattern as this pattern provides one of the best ways to create an object.','We are going to create a Shape and Color interfaces and concrete classes implementing these interfaces. We create an abstract factory class AbstractFactory as next step. Factory classes ShapeFactory and ColorFactory are defined where each factory extends AbstractFactory. A factory creator/generator class FactoryProducer is created.','icon_abstract_pattern');
COMMIT;
