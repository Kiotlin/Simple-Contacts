package MyContracts;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;

public class MyContracts extends JFrame {

    private JPanel jp1;
    private JButton jb1,jb2,jb3,jb4;

    private Object[] columnNames = {"学生编号","学生姓名","学生性别","学生年龄"
                                    ,"出生日期","手机号码","家庭住址","电子邮箱","专业" };
    private Object[][] studentInfo = new Object[20][9];
    private Object[][] studentInfo1 = new Object[20][9];

    private JTable table = new JTable(studentInfo,columnNames);
    private JTable table1 = new JTable(studentInfo1,columnNames);

    private File xmlFile;
    private org.w3c.dom.Document doc;

    /******************************************************\
     *
     * //////////////创建信息"添加/编辑"界面GUI///////////////
     *
     ******************************************************/
    class CreateStudentInformation extends JFrame{

        String[] Date = new String[60];

        private JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8,jl9;
        private JTextField jf1,jf2,jf3,jf4,jf5,jf6,jf7;
        private JRadioButton jrb1,jrb2;
        private JComboBox jComboBox;
        private JButton jb1,jb2;
        private JPanel jp;
        private ButtonGroup buttongroup;

        CreateStudentInformation(){

            jl1=new JLabel("学生编号");
            jl2=new JLabel("学生姓名");
            jl3=new JLabel("学生性别");
            jl4=new JLabel("学生年龄");
            jl5=new JLabel("出生日期");
            jl6=new JLabel("手机号码");
            jl7=new JLabel("电子邮箱");
            jl8=new JLabel("家庭住址");
            jl9=new JLabel("专业");

            jf1=new JTextField();
            jf2=new JTextField();
            jf3=new JTextField();
            jf4=new JTextField();
            jf5=new JTextField();
            jf6=new JTextField();
            jf7=new JTextField();

            buttongroup=new ButtonGroup();
            jrb1=new JRadioButton("男");
            jrb2=new JRadioButton("女");
            buttongroup.add(jrb1);
            buttongroup.add(jrb2);

            /**
             * 为下拉菜单创建年份月份
             * @from
             * @1996/01
             * @to
             * @2000/12
             * */
            int count = 0;
            for(int i = 1996;i <= 2000; i++){
                for(int j = 1;j <= 12; j++){
                    Date[count] = i + "年" + j + "月";
                    count++;
                }
            }
            jComboBox=new JComboBox(Date);

            jb1=new JButton("添加");
            jb2=new JButton("关闭");

            jp=new JPanel();
            Border titleBorder1=BorderFactory.createTitledBorder("添加学生信息");
            jp.setBorder(titleBorder1);
            jp.setLayout(null);

            this.setVisible(true);
            this.setBounds(200,200,300,380);

            jp.add(jl1);
            jl1.setBounds(30,20,70,20);
            jp.add(jl2);
            jl2.setBounds(30,50,70,20);
            jp.add(jl3);
            jl3.setBounds(30,80,70,20);
            jp.add(jl4);
            jl4.setBounds(30,110,70,20);
            jp.add(jl5);
            jl5.setBounds(30,140,70,20);
            jp.add(jl6);
            jl6.setBounds(30,170,70,20);
            jp.add(jl7);
            jl7.setBounds(30,200,70,20);
            jp.add(jl8);
            jl8.setBounds(30,230,70,20);
            jp.add(jl9);
            jl9.setBounds(30,260,70,20);

            jp.add(jf1);
            jf1.setBounds(110,20,150,20);
            jp.add(jf2);
            jf2.setBounds(110,50,150,20);
            jp.add(jrb1);
            jrb1.setBounds(110,80,50,20);
            jp.add(jrb2);
            jrb2.setBounds(170,80,50,20);
            jp.add(jf3);
            jf3.setBounds(110,110,150,20);
            jp.add(jComboBox);
            jComboBox.setBounds(110,140,150,20);
            jp.add(jf4);
            jf4.setBounds(110,170,150,20);
            jp.add(jf5);
            jf5.setBounds(110,200,150,20);
            jp.add(jf6);
            jf6.setBounds(110,230,150,20);
            jp.add(jf7);
            jf7.setBounds(110,260,150,20);
            jp.add(jb1);
            jb1.setBounds(25,300,110,20);
            jp.add(jb2);
            jb2.setBounds(150,300,110,20);

            jb2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dispose();
                }
            });

            this.add(jp);
            this.setResizable(false);
        }
    }

    /******************************************************\
     *
     * //////////////创建"查找"界面GUI///////////////
     *
     ******************************************************/
    class SearchStudentInformation extends JFrame{

        JPanel jPanel;

        JComboBox<String> jComboBox;

        JTextField jTextField;

        JButton jb1,jb2;

        JLabel jl = new JLabel("查找项目");

        SearchStudentInformation(){

            this.setLayout(null);

            String[] items = new String[]{"学生姓名","性别","年龄","出生日期","家庭住址","专业"};

            jComboBox = new JComboBox<>(items);

            jTextField = new JTextField();

            jb1 = new JButton("查询");
            jb2 = new JButton("关闭");

            jPanel = new JPanel();
            jPanel.setLayout(null);

            jPanel.add(jl);
            jl.setBounds(20,10,60,20);
            jPanel.add(jComboBox);
            jComboBox.setBounds(90,10,90,20);
            jPanel.add(jTextField);
            jTextField.setBounds(190,10,120,20);
            jPanel.add(jb1);
            jb1.setBounds(500,10,60,20);
            jPanel.add(jb2);
            jb2.setBounds(590,10,60,20);

            this.add(jPanel);
            jPanel.setBounds(0,0,700,30);

            DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
            renderer1.setHorizontalAlignment(JLabel.CENTER);
            table1.setDefaultRenderer(Object.class,renderer1);
            //给表格添加滚动条
            JScrollPane js1 = new JScrollPane(table1);
            this.add(js1);
            js1.setBounds(0,41,700,220);

            this.setVisible(true);
            this.setBounds(200,200,700,300);

            jb2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dispose();
                }
            });

        }
    }

    /******************************************************\
     *
     * ////////////////////加载xml文件///////////////////////
     *
     ******************************************************/
    public org.w3c.dom.Document getDocument() throws Exception{
        //加载XML
        xmlFile = new File("res/book.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        org.w3c.dom.Document doc = builder.parse(xmlFile);

        return doc;

    }

    /******************************************************\
     *
     * ///////////////////创建新xml标签//////////////////////
     *
     ******************************************************/
    public Node CreateStudentNode(org.w3c.dom.Document doc, String id,
                                  String name, String sex, String age,
                                  String birth, String tel, String mail,
                                  String address, String major){

        org.w3c.dom.Element student2 = doc.createElement("student");

        try{
            org.w3c.dom.Element id2 = doc.createElement("id");
            org.w3c.dom.Element name2 = doc.createElement("name");
            org.w3c.dom.Element sex2 = doc.createElement("sex");
            org.w3c.dom.Element age2 = doc.createElement("age");
            org.w3c.dom.Element birth2 = doc.createElement("birth");
            org.w3c.dom.Element tel2 = doc.createElement("tel");
            org.w3c.dom.Element address2 = doc.createElement("address");
            org.w3c.dom.Element mail2 = doc.createElement("mail");
            org.w3c.dom.Element major2 = doc.createElement("major");

            id2.setTextContent(id);
            name2.setTextContent(name);
            sex2.setTextContent(sex);
            age2.setTextContent(age);
            birth2.setTextContent(birth);
            tel2.setTextContent(tel);
            address2.setTextContent(mail);
            mail2.setTextContent(address);
            major2.setTextContent(major);

            student2.appendChild(id2);
            student2.appendChild(name2);
            student2.appendChild(sex2);
            student2.appendChild(age2);
            student2.appendChild(birth2);
            student2.appendChild(tel2);
            student2.appendChild(address2);
            student2.appendChild(mail2);
            student2.appendChild(major2);

        }catch (Exception e ){

            e.printStackTrace();

        }

        return student2;
    }

    /******************************************************\
     *
     * ///////////////////顶层按钮单击事件////////////////////
     *
     ******************************************************/
    //"添加"按钮
    class AddInformation implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

           CreateStudentInformation AddStudentInformation = new CreateStudentInformation();

           AddStudentInformation.jb1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    String sex = "";

                    if(AddStudentInformation.jf1.getText().equals("")){
                        JOptionPane.showMessageDialog(AddStudentInformation,"请填写学生编号！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(AddStudentInformation.jf2.getText().equals("")){
                        JOptionPane.showMessageDialog(AddStudentInformation,"请填写学生姓名！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(AddStudentInformation.jf3.getText().equals("")){
                        JOptionPane.showMessageDialog(AddStudentInformation,"请填写学生年龄！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(AddStudentInformation.jf4.getText().equals("")){
                        JOptionPane.showMessageDialog(AddStudentInformation,"请填写手机号码！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(AddStudentInformation.jf6.getText().equals("")){
                        JOptionPane.showMessageDialog(AddStudentInformation,"请填写家庭住址！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(AddStudentInformation.jf7.getText().equals("")){
                        JOptionPane.showMessageDialog(AddStudentInformation,"请填写学生专业！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(!AddStudentInformation.jrb1.isSelected()&&!AddStudentInformation.jrb2.isSelected()){
                        JOptionPane.showMessageDialog(AddStudentInformation,"请选择学生性别！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(AddStudentInformation.jrb1.isSelected()){
                        sex = AddStudentInformation.jrb1.getText();
                    }
                    else if(AddStudentInformation.jrb2.isSelected()){
                        sex = AddStudentInformation.jrb2.getText();
                    }

                    try{

                        Node root = doc.getFirstChild();//得到根节点
                        //System.out.println(root.getNodeName());
                        //创建新节点
                        root.appendChild(CreateStudentNode(doc,AddStudentInformation.jf1.getText(),AddStudentInformation.jf2.getText(),
                                sex, AddStudentInformation.jf3.getText(),(String)AddStudentInformation.jComboBox.getSelectedItem(),
                                AddStudentInformation.jf4.getText(),AddStudentInformation.jf5.getText(),AddStudentInformation.jf6.getText(),AddStudentInformation.jf7.getText()));

                        //对新加的信息进行保护
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        transformer.transform(new DOMSource(root),new StreamResult(new FileOutputStream(xmlFile)));
                        //重新加载表格
                        JOptionPane.showMessageDialog(AddStudentInformation,"添加成功","提示",JOptionPane.INFORMATION_MESSAGE);
                        setTable(table);

                        AddStudentInformation.dispose();

                    }catch (Exception ex ){

                        ex.printStackTrace();

                    }

                }
            });
        }
    }

    //"编辑"按钮
    class EditInformation implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if(table.getSelectedColumnCount()==0){
                JOptionPane.showMessageDialog(MyContracts.this,"请选择你要编辑的数据行！"
                        ,"WARNING",JOptionPane.WARNING_MESSAGE);
            }
            else if(table.getSelectedColumnCount()>1){
                JOptionPane.showMessageDialog(MyContracts.this,"你每次只能编辑一个数据行！"
                        ,"WARNING",JOptionPane.WARNING_MESSAGE);
            }

            CreateStudentInformation ExsitedInformation = new CreateStudentInformation();
            ExsitedInformation.jf1.setText((String)table.getValueAt(table.getSelectedRow(),0));
            ExsitedInformation.jf2.setText((String)table.getValueAt(table.getSelectedRow(),1));
            ExsitedInformation.jf3.setText((String)table.getValueAt(table.getSelectedRow(),3));
            ExsitedInformation.jf4.setText((String)table.getValueAt(table.getSelectedRow(),5));
            ExsitedInformation.jf5.setText((String)table.getValueAt(table.getSelectedRow(),6));
            ExsitedInformation.jf6.setText((String)table.getValueAt(table.getSelectedRow(),7));
            ExsitedInformation.jf7.setText((String)table.getValueAt(table.getSelectedRow(),8));
            ExsitedInformation.jComboBox.setSelectedItem(table.getValueAt(table.getSelectedRow(),4));

            if(ExsitedInformation.jrb1.getText().equals((String)table.getValueAt(table.getSelectedRow(),2))){
                ExsitedInformation.jrb1.doClick();
            }
            else if(ExsitedInformation.jrb2.getText().equals((String)table.getValueAt(table.getSelectedRow(),2))){
                ExsitedInformation.jrb2.doClick();
            }

            ExsitedInformation.jb1.setText("修改");
            ExsitedInformation.jb1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    String sex = "";

                    if(ExsitedInformation.jf1.getText().equals("")){
                        JOptionPane.showMessageDialog(ExsitedInformation,"请填写学生编号！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(ExsitedInformation.jf2.getText().equals("")){
                        JOptionPane.showMessageDialog(ExsitedInformation,"请填写学生姓名！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(ExsitedInformation.jf3.getText().equals("")){
                        JOptionPane.showMessageDialog(ExsitedInformation,"请填写学生年龄！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(ExsitedInformation.jf4.getText().equals("")){
                        JOptionPane.showMessageDialog(ExsitedInformation,"请填写手机号码！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(ExsitedInformation.jf6.getText().equals("")){
                        JOptionPane.showMessageDialog(ExsitedInformation,"请填写家庭住址！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(ExsitedInformation.jf7.getText().equals("")){
                        JOptionPane.showMessageDialog(ExsitedInformation,"请填写学生专业！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(!ExsitedInformation.jrb1.isSelected()&&!ExsitedInformation.jrb2.isSelected()){
                        JOptionPane.showMessageDialog(ExsitedInformation,"请选择学生性别！",
                                "WARNING",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(ExsitedInformation.jrb1.isSelected()){
                        sex = ExsitedInformation.jrb1.getText();
                    }
                    else if(ExsitedInformation.jrb2.isSelected()){
                        sex = ExsitedInformation.jrb2.getText();
                    }

                    try{

                        org.w3c.dom.Element root = doc.getDocumentElement();
                        //替换节点
                        root.replaceChild(
                                CreateStudentNode(doc,ExsitedInformation.jf1.getText(),ExsitedInformation.jf2.getText(),
                                        sex, ExsitedInformation.jf3.getText(),(String)ExsitedInformation.jComboBox.getSelectedItem(),
                                        ExsitedInformation.jf4.getText(),ExsitedInformation.jf5.getText(),ExsitedInformation.jf6.getText(),ExsitedInformation.jf7.getText()),
                                root.getElementsByTagName("sex").item(table.getSelectedRow()).getParentNode());

                        TransformerFactory transformerFactory = TransformerFactory
                                .newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        transformer.transform(new DOMSource(root), new StreamResult(
                                new FileOutputStream(xmlFile)));

                        JOptionPane.showMessageDialog(ExsitedInformation,"修改成功！","提示",JOptionPane.INFORMATION_MESSAGE);

                        setTable(table);

                        ExsitedInformation.dispose();

                    }catch (Exception ex){

                        ex.printStackTrace();

                    }
                }
            });
        }
    }

    //"删除"按钮
    class DeleteInformation implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(table.getSelectedColumnCount()==0){
                JOptionPane.showMessageDialog(MyContracts.this,"请选择你要删除的数据行！"
                        ,"WARNING",JOptionPane.WARNING_MESSAGE);
            }
            else if(table.getSelectedColumnCount()>1){
                JOptionPane.showMessageDialog(MyContracts.this,"你每次只能删除一个数据行！"
                        ,"WARNING",JOptionPane.WARNING_MESSAGE);
            }

            try{

                org.w3c.dom.Element root = doc.getDocumentElement();

                // 这边模拟删除，我设置性别这项不可能为空，所以利用性别来找到其父元素，根据父元素来删除
                root.removeChild(root.getElementsByTagName("sex")
                        .item(table.getSelectedRow()).getParentNode());

                TransformerFactory transformerFactory = TransformerFactory
                        .newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.transform(new DOMSource(root), new StreamResult(
                        new FileOutputStream(xmlFile)));

                setTable(table);

                JOptionPane.showMessageDialog(MyContracts.this, "删除成功！", "提示",
                        JOptionPane.INFORMATION_MESSAGE);

            }catch (Exception exx){

                exx.printStackTrace();

            }
        }
    }

    //"查找"按钮
    class SearchInformation implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            SearchStudentInformation searchStudentInformation = new SearchStudentInformation();

            searchStudentInformation.jb1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);

                    String SearchByName = "";

                    if(searchStudentInformation.jComboBox.getSelectedItem().toString().equals("学生姓名")){
                        SearchByName = searchStudentInformation.jTextField.getText();
                    }

                    try{

                        for (int i = 0; i < table1.getRowCount(); i++) {

                            studentInfo1[i][0] = "";
                            studentInfo1[i][1] = "";
                            studentInfo1[i][2] = "";
                            studentInfo1[i][3] = "";
                            studentInfo1[i][4] = "";
                            studentInfo1[i][5] = "";
                            studentInfo1[i][6] = "";
                            studentInfo1[i][7] = "";
                            studentInfo1[i][8] = "";

                        }

                        for(int i = 0; i <table.getRowCount(); i++){

                            if(searchStudentInformation.jComboBox.getSelectedItem().toString().equals("学生姓名")){

                                int count=0;
                                if(table.getValueAt(i,1).toString().equals(SearchByName)){

                                    table1.setValueAt(table.getValueAt(i,0),count,0);
                                    table1.setValueAt(table.getValueAt(i,1),count,1);
                                    table1.setValueAt(table.getValueAt(i,2),count,2);
                                    table1.setValueAt(table.getValueAt(i,3),count,3);
                                    table1.setValueAt(table.getValueAt(i,4),count,4);
                                    table1.setValueAt(table.getValueAt(i,5),count,5);
                                    table1.setValueAt(table.getValueAt(i,6),count,6);
                                    table1.setValueAt(table.getValueAt(i,7),count,7);
                                    table1.setValueAt(table.getValueAt(i,8),count,8);

                                }
                            }
                        }

                    }catch (Exception e1){

                        e1.printStackTrace();

                    }

                }
            });

        }
    }
    /******************************************************\
     *
     * /////////////////////初始化数据表//////////////////////
     *
     ******************************************************/
    public void setTable(JTable table){

        for (int i = 0; i < table.getRowCount(); i++) {
            studentInfo[i][0] = "";
            studentInfo[i][1] = "";
            studentInfo[i][2] = "";
            studentInfo[i][3] = "";
            studentInfo[i][4] = "";
            studentInfo[i][5] = "";
            studentInfo[i][6] = "";
            studentInfo[i][7] = "";
            studentInfo[i][8] = "";
        }

        try{

            doc = getDocument();
            org.w3c.dom.Element root = doc.getDocumentElement();

            NodeList id = root.getElementsByTagName("id");
            NodeList name = root.getElementsByTagName("name");
            NodeList sex = root.getElementsByTagName("sex");
            NodeList age = root.getElementsByTagName("age");
            NodeList birth = root.getElementsByTagName("birth");
            NodeList tel = root.getElementsByTagName("tel");
            NodeList address = root.getElementsByTagName("address");
            NodeList mail = root.getElementsByTagName("mail");
            NodeList major = root.getElementsByTagName("major");

            for(int i= 0 ; i < name.getLength(); i++){

                studentInfo[i][0] = id.item(i).getTextContent();
                studentInfo[i][1] = name.item(i).getTextContent();
                studentInfo[i][2] = sex.item(i).getTextContent();
                studentInfo[i][3] = age.item(i).getTextContent();
                studentInfo[i][4] = birth.item(i).getTextContent();
                studentInfo[i][5] = tel.item(i).getTextContent();
                studentInfo[i][6] = address.item(i).getTextContent();
                studentInfo[i][7] = mail.item(i).getTextContent();
                studentInfo[i][8] = major.item(i).getTextContent();

            }

            this.repaint();

        }catch (Exception e ){

            e.printStackTrace();
        }
    }

    /******************************************************\
     *
     * ///////////////初始化软件主界面GUI////////////////
     *
     ******************************************************/
    public void init(){

        this.setVisible(true);
        this.setLayout(null);
        this.setTitle("通讯录");

        jp1=new JPanel();
        jp1.setLayout(new GridLayout());

        jb1=new JButton("添加");
        jb2=new JButton("编辑");
        jb3=new JButton("删除");
        jb4=new JButton("查找");
        jb1.addActionListener(new AddInformation());//单击"添加"弹出添加信息窗口
        jb2.addActionListener(new EditInformation());//单击"编辑"弹出编辑信息窗口
        jb3.addActionListener(new DeleteInformation());//单击"删除"删除数据行
        jb4.addActionListener(new SearchInformation());

        jp1.add(jb1);
        jp1.add(jb2);
        jp1.add(jb3);
        jp1.add(jb4);
        jp1.setBounds(0,0,500,30);//顶部Button条设定

        //设置表格内容居中显示
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,renderer);
        //给表格添加滚动条
        JScrollPane js = new JScrollPane(table);
        js.setBounds(0,31,800,220);
        //创建表格
        setTable(table);

        this.add(jp1);
        this.add(js);
        this.setBounds(100,100,800,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    MyContracts(){
        init();
    }

    public static void main(String[] args){
        MyContracts mc=new MyContracts();
    }
}



