package com.addresbook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by seoyulim on 2017. 4. 17..
 */
public class contactPanel extends JPanel {

    JTextField name = new JTextField("Name"); // add 할 때 dialog에 나타날 textfield
    JTextField number = new JTextField("010-xxxx-xxxx"); // add 할 때 dialog에 나타날 textfield
    JTextField email = new JTextField("yurim@example.com"); // add 할 때 dialog에 나타날 textfield

    boolean firstExecute = true;

    JTextField textfName = new JTextField(10); //  삭제할 객체의 이름을 입력할 textfiled
    JButton findByNamebtn = new JButton("FindByName");
    JButton delByNumbbtn = new JButton("DelByNumb");

    JTextField textfNumber = new JTextField(10); //  삭제할 객체의 번호 입력할 textfiled
    JButton delByNamebtn = new JButton("DelByName");
    JButton findByNumbbtn = new JButton("FindByNumb");

    JButton loadBtn = new JButton("Load Data");
    JButton saveBtn = new JButton("Save Data");
    JButton addBtn = new JButton("Add Data");

    JPanel contactMenu = new JPanel(); // 옵션 메뉴들을 붙일 panel
    JPanel topMenu = new JPanel(); // 옵션 메뉴들을 붙여줄 callMenu, jpanel로 만듦
    JPanel centerMenu = new JPanel(); // 옵션 메뉴들을 붙여줄 callMenu, jpanel로 만듦
    JPanel bottomMenu = new JPanel(); // 옵션 메뉴들을 붙여줄 callMenu, jpanel로 만듦


    static JTextArea contactContent = new JTextArea(29, 30); // 현재 패널에 삽입될 text area, 전화 기록들이 표시될 공간
    JScrollPane midScroll = new JScrollPane(contactContent); // midContent 에 생길 scroll bar
    static Vector<Person> contactData= new Vector<Person>();
    static HashMap<String, Person> contactMap = new HashMap<String, Person >();


    public contactPanel(){

        textfName.setText("Name");
        textfNumber.setText("010-xxxx-xxxx");
        // 기본 입력 양식을 세팅해둔다.

        if(firstExecute) {
            contactData = JRead.readPerson();
            contactData = Person.sortEntry(contactData);
            // 이름 순 정렬 ( 오름차순 정렬 )
            firstExecute = false;
            contactMap = Person.createNumberMap(contactData);
        }

        contactData = Person.sortEntry(contactData);// 이름순 정렬
        Display();



        this.add(midScroll, BorderLayout.CENTER);



        // 커서가 입력되면, 텍스트필드의 입력 양식이 사라진다
        textfNumber.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textfNumber.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });


        number.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                number.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });


        textfName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textfName.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                name.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        email.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                email.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });


        //레이아웃과 배경색 설정
        this.setLayout(new BorderLayout());
        this.setBackground(Color.getHSBColor((float)2.00,(float)0.15, (float)1.0));
        topMenu.setBackground(Color.getHSBColor((float)2.00,(float)0.15, (float)1.0));
        centerMenu.setBackground(Color.getHSBColor((float)2.00,(float)0.15, (float)1.0));
        bottomMenu.setBackground(Color.getHSBColor((float)2.00,(float)0.15, (float)1.0));
        contactMenu.setBackground(Color.getHSBColor((float)2.00,(float)0.15, (float)1.0));

        midScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        midScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        this.add(midScroll, BorderLayout.CENTER);

        contactMenu.setLayout(new BorderLayout());
        topMenu.add(textfName);
        topMenu.add(findByNamebtn);
        topMenu.add(delByNamebtn);
        contactMenu.add(topMenu, BorderLayout.NORTH);


        centerMenu.add(textfNumber);
        centerMenu.add(findByNumbbtn);
        centerMenu.add(delByNumbbtn);
        contactMenu.add(centerMenu, BorderLayout.CENTER);


        bottomMenu.add(loadBtn);
        bottomMenu.add(saveBtn);
        bottomMenu.add(addBtn);
        contactMenu.add(bottomMenu, BorderLayout.SOUTH);

        ButtonListener btnListener  = new ButtonListener();

        // 버튼 리스너를 달아준다
        addBtn.addActionListener(btnListener);
        findByNamebtn.addActionListener(btnListener);
        delByNamebtn.addActionListener(btnListener);
        findByNumbbtn.addActionListener(btnListener);
        delByNumbbtn.addActionListener(btnListener);
        saveBtn.addActionListener(btnListener);
        loadBtn.addActionListener(btnListener);
        this.add(contactMenu, BorderLayout.SOUTH);

    }

    static void Display (){

        //  TextArea에 보여줄 함수

        contactContent.setText("   Name      " +  "|   Phone number  |" + "  E-mail\n" );
        for(int i=0 ; i < contactData.size() ; i++) {

            // String s = " "+contactData.elementAt(i).getName_() + "| ";

            contactContent.append(String.format(" %-13s |", contactData.elementAt(i).getName_()));
            contactContent.append(String.format(" %-15s |", contactData.elementAt(i).getNumber_()));
            contactContent.append(String.format(" %-20s |", contactData.elementAt(i).getEmail_()));
            //append 메소드를 통해 textArea에 내용을 추가 한다

            contactContent.append("\n");
        }

    }

    class ButtonListener implements ActionListener{

        Object[] Content = {
            "Name:", name,
            "Number", number,
            "Email", email
        };
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Add Data")) {

                //기본 글자 세팅
                name.setText("Name");
                number.setText("010-xxxx-xxxx");
                email.setText("yurim@example.com");

                int result = JOptionPane.showConfirmDialog(null, Content, "전화번호부에 객체를 추가합시다",JOptionPane.OK_CANCEL_OPTION);
                // add할 person 객체의 정보를 다 입력하고 확인버튼을 누를 시
                if(result == JOptionPane.OK_OPTION) {
                    Person p = new Person(name.getText(), number.getText(), email.getText());
                    contactData.add(p); // vector에 정보 추가
                    contactMap.put(p.getNumber_(), p); // map에 정보 추가
                    contactData = Person.sortEntry(contactData);
                    Display();
                }

            }
            else if(e.getActionCommand().equals("FindByName")) {
                // 이진 탐색으로 가져온 데이터 저장
                Person p = Person.findPersonByName(contactData,textfName.getText());
                if(p==null) // 못찾으면 person객체가 null이므로 에러 메시지
                    JOptionPane.showMessageDialog(null, "No Data","Find Error",JOptionPane.ERROR_MESSAGE);
                else {
                    // 찾은 데이터를 dialog로 띄운다
                    String data = p.getNumber_() + "  " + p.getEmail_();
                    JOptionPane.showMessageDialog(null, data,"Find Data",JOptionPane.PLAIN_MESSAGE);

                }
            }
            else if(e.getActionCommand().equals("DelByName")){
                Vector<Person> temp = Person.deleteByName(contactData, textfName.getText());


                if(temp !=null) {
                    contactData = temp;
                    Person p = Person.findPersonByName(contactData,textfName.getText());
                    contactMap = Person.createNumberMap(contactData); // 수정된 person vector를 불러와 map을 초기화 한다
                    Display();
                }
                else//못찾으면 person 객체가 null이므로 에러 메시지
                    JOptionPane.showMessageDialog(null, "No Data","Delete Error",JOptionPane.ERROR_MESSAGE);

            }
            else if(e.getActionCommand().equals("FindByNumb")) {
                // HashMap의 get 함수를 통해 value를 바로 매칭해 가져온다. 시간복잡도 상수
                Person p = contactMap.get(textfNumber.getText());
                // 못찾으면 person객체가 null이므로 에러 메시지
                if(p==null)
                    JOptionPane.showMessageDialog(null, "No Data","Find Error",JOptionPane.ERROR_MESSAGE);
                else {
                    String data = p.getName_() + "  " + p.getEmail_();
                    JOptionPane.showMessageDialog(null, data,"Find Data",JOptionPane.PLAIN_MESSAGE);

                }
            }
            else if(e.getActionCommand().equals("DelByNumb")){

                Person p = contactMap.get(textfNumber.getText());

                // 못찾으면 person객체가 null이므로 에러 메시지
                if( p==null) {
                    JOptionPane.showMessageDialog(null, "No Data","Delete Error",JOptionPane.ERROR_MESSAGE);

                }
                else {

                    String name = contactMap.get(textfNumber.getText()).getName_();
                    contactData = Person.deleteByName(contactData, name); // vector에서도 지워준다
                    Display();
                    contactMap.remove(textfNumber.getText());

                }

            }
            else if(e.getActionCommand().equals("Save Data")) {
                contactData = Person.sortEntry(contactData);// 저장하기 전에 소트
                JWrite.savePerson(contactData);
            }
            else if(e.getActionCommand().equals("Load Data")) {
                contactData = JRead.readPerson();// json에서 데이터를 불러와 로컬에 저장
                contactMap = (Person.createNumberMap(contactData));// 불러온 데이터를 map에도 저장
                Display();
            }
        }

    }




}