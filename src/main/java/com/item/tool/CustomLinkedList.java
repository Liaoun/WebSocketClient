package com.item.tool;

/**
 * 自定义linkedlist
 */
public class CustomLinkedList<T> {

    private ListData<T> head=new ListData();

    private int length=-1;
    public void add(T t){
        ListData temp=head;
        while (temp.next!=null){
            temp=temp.next;
        }
        ListData listData= new ListData();
        listData.value=t;
        temp.next=listData;
        length++;

    }

    public void reversalList(){
        ListData<T> temp=head;
        if (temp.next==null){
            return;
        }
        ListData<T> reversal=new ListData<>();
        while (temp.next!=null){
            temp=temp.next;
            if (reversal.next!=null){
                ListData<T> listData=new ListData<>();
                listData.next =reversal.next;
                listData.value=temp.value;
                reversal.next=listData;
            }else {
                ListData<T> listData=new ListData<>();
                listData.value=temp.value;
                reversal.next=listData;
            }
        }
        ListData<T> temp2=reversal;

        while (reversal.next!=null){
            reversal=reversal.next;
            System.out.println(reversal.value);
        }
    }

    public void remove(int i){
        ListData temp=head;
        if(i>length){
            System.out.println("下标溢出");
            return;
        }
        int len=0;
        while (temp.next!=null){
            if (len==i){
                break;
            }
            temp=temp.next;
            len++;
        }
        ListData listData=temp.next.next;
        temp.next=listData;
        length--;
    }

    public int getLength(){
        return length+1;
    }

    public void getList(){
        ListData temp=head;
        if (temp.next==null){
            System.out.println("list中没有数据");
        }
        while (temp.next!=null){
            temp=temp.next;
            System.out.println(temp.value);
        }
    }


    class ListData<T>{
        public T value;
        private ListData next;
    }
}
