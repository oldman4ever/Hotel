package testing;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import hotel.IData;
import hotel.InHotel;

public class InHotelMockTest_2 {
    private Mockery context=new Mockery();
    private IData iData=null;
    private InHotel ih=null;
    private InHotel oh=null;
	
    @Before
    public void setUp() throws Exception{
   	 iData=context.mock(IData.class);
   	 ih=new InHotel(iData);
   	
   	 context.checking(new Expectations(){{
   		allowing(iData).in_Out_Room(601, "Mary");
   		will(onConsecutiveCalls(returnValue("Mary成功入住901房间！"),returnValue("该房间已经有人入住")));	
   		 }});	
   	 iData=context.mock(IData.class);
   	 oh=new InHotel(iData);
  	
  	 context.checking(new Expectations(){{
  		allowing(iData).in_Out_Room(601, "EMPTY");
  		will(onConsecutiveCalls(returnValue("601退房成功！"),returnValue("该房间没有客人入住，退房失败！")));	
  		 }});	
     }
  
    @Test
	public void testIn() {
          //测试
		assertEquals("Marry成功入住901房间！",ih.in(601, "Mary"));
		assertEquals("该房间已经有人入住",ih.in(601, "Mary"));
		assertEquals("601退房成功!",oh.in(601, "EMPTY"));
		assertEquals("该房间没有客人入住，退房失败！",oh.in(601, "EMPTY"));
	}

}
