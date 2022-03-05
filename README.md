# About
DynVar is an easier way to manage attribute-like variables for Java, created for use in Game Development.

I have been using variants of this in most of my online server project. This version is a more production-worthy attempt of those variants. this is a map-like class that supports an easy to use method-chaining API.

# How To
#### Creating a new instance
To create a new instance all you need to do is create a new VarMap object.
```
VarMap map = new VarMap();
```
#### Serializing/saving & loading from disk
You can serialize the map using the built-in serializer, it uses it's own format similar to .ini.
Deserializing supports .ini format as well if you perfer to have hand-written configurations.
```
new VarMapSerializer("map.ini", map, true).save();
new VarMapSerializer("map.ini", map, true).load();
```
#### Creating new variables
To create a new variable all you need to do is define the type first, then enter the unique variable name you would like to label it.
```
boolean existsAlready = map.getBoolean("githubBoolExample").get();
```
To supply a default value you can do it on type constructor
```
boolean existsAlready = map.getBoolean("githubBoolExample", false).get();
```
#### Method chainning
To fully leverage DynVar the trick is to chain methods to create easy-to-read one liners.
```
//create a new variable if it doesn't exist yet and check if it's been set to true, if it has execute the code below
if(map.getBoolean("githubBoolExample").get())
```
```
//create a new variable if it doesn't exist yet, check if it's been set as true, if it hasn't execute the code below, either way set it to being set as true
if(!map.getBoolean("githubBoolExample").getAndSet(true))
```
```
//create a new variable if it doesn't exist yet, add one, check if it equals any of the numbers, if it does execute the code below
if(map.getInt("githubIntExample").add(1).equalsAny(13, 21, 34, 55, 89))
```
#### Time & Custom Types
DynVar supports custom types, one built-in example common is system time interaction. The Time type is based on the Long type, this means it comes with all of the helper functions such as add, subtract, divide and multiply.
```
map.getTime("githubTimeExample").setNow(); //set the stop-watch to start counting now
map.getTime("githubTimeExample").hasPassed(30_000); //return true if 30 seconds have passed
```
