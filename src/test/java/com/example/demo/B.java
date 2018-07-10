package com.example.demo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class B {

	public static final  List<SocketChannel> channels=new ArrayList<>();
	public static void main(String[] args) {
				try {
					Selector selector =Selector.open();
					ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
					ServerSocket serverSocket=serverSocketChannel.socket();
					serverSocket.bind(new InetSocketAddress(8982));
					serverSocketChannel.configureBlocking(false);
					serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
					
					while (true) {
						int x=selector.select();
						if(x>0) {
							 Iterator<SelectionKey> iterator=selector.selectedKeys().iterator();
							 while(iterator.hasNext()) {
								 SelectionKey key=iterator.next();
								 if(key.isAcceptable()) {
									 ServerSocketChannel channel 
									 = (ServerSocketChannel) key.channel();
									 SocketChannel socket =channel.accept();
									 socket.configureBlocking(false);
									 socket.register(selector, SelectionKey.OP_READ);
								 }
								 if(key.isValid()&&key.isReadable()) {
									 readMsg(key);
								 }
								 if(key.isValid()&&key.isWritable()) {
									 writeMsg(key);
								 }
								 iterator.remove();
							 }
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	}

	public static void writeMsg(SelectionKey key) {
		System.out.println(key.hashCode());
	}

	public static void readMsg(SelectionKey key) {
		SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            String msg=null;
            if(count > 0){
                //让buffer翻转，把buffer中的数据读取出来
                buffer.flip();
                msg=new String(buffer.array(), 0, count);
            }
            System.out.println(msg);
        }catch (Exception e) {
		}
       
	}
	
}
