package edu.course.lab04;
import java.util.HashMap;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AcademicContractsTest {
 @Test void bookByIsbn() {
   var index=new HashMap<Book,String>();
   var first=new Book("978-1","Original");
   var second=new Book("978-1","Reprint");
   index.put(first,"shelf");
   assertEquals("shelf",index.get(second));
   assertEquals(first.hashCode(),second.hashCode());
   assertNotEquals(first,new Book("978-2","Original"));
 }
 @Test void equalKeysReplaceAndRemove() {
   var registry=new SampleRegistry();
   var first=new SampleId("iris",7);
   var second=new SampleId("iris",7);
   var old=new DataSample("old");
   var newest=new DataSample("new");
   registry.put(first,old);
   assertSame(old,registry.get(second));
   assertTrue(registry.containsKey(second));
   registry.put(second,newest);
   assertEquals(1,registry.size());
   assertSame(newest,registry.remove(first));
   assertFalse(registry.containsKey(first));
   assertEquals(0,registry.size());
 }
 @Test void unequalKeysCollideWithoutDataLoss() {
   var first=new SampleId("Aa",1);
   var second=new SampleId("BB",1);
   assertNotEquals(first,second);
   assertEquals(first.hashCode(),second.hashCode());
   var index=new SampleRegistry();
   var a=new DataSample("a");
   var b=new DataSample("b");
   index.put(first,a);
   index.put(second,b);
   assertSame(a,index.get(first));
   assertSame(b,index.get(second));
   assertEquals(2,index.size());
 }
 @Test void equalityRejectsUnrelatedObjects() {
   var key=new SampleId("iris",1);
   assertNotEquals(key,null);
   assertNotEquals(key,"iris");
 }
 @Test void mutableKeyCanLoseLookup() {
   class Key {
     int id=1;
     public int hashCode(){return id;}
     public boolean equals(Object value){return value instanceof Key other && id==other.id;}
   }
   var key=new Key();
   var map=new HashMap<Key,String>();
   map.put(key,"record");
   assertEquals("record",map.get(key));
   key.id=2;
   assertNull(map.get(key));
 }
}
