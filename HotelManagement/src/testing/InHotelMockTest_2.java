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
   		will(onConsecutiveCalls(returnValue("Mary�ɹ���ס901���䣡"),returnValue("�÷����Ѿ�������ס")));	
   		 }});	
   	 iData=context.mock(IData.class);
   	 oh=new InHotel(iData);
  	
  	 context.checking(new Expectations(){{
  		allowing(iData).in_Out_Room(601, "EMPTY");
  		will(onConsecutiveCalls(returnValue("601�˷��ɹ���"),returnValue("�÷���û�п�����ס���˷�ʧ�ܣ�")));	
  		 }});	
     }
  
    @Test
	public void testIn() {
          //����
		assertEquals("Marry�ɹ���ס901���䣡",ih.in(601, "Mary"));
		assertEquals("�÷����Ѿ�������ס",ih.in(601, "Mary"));
		assertEquals("601�˷��ɹ�!",oh.in(601, "EMPTY"));
		assertEquals("�÷���û�п�����ס���˷�ʧ�ܣ�",oh.in(601, "EMPTY"));
	}

}
