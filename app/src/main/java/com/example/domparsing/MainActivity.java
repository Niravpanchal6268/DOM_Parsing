package com.example.domparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout =findViewById(R.id.main_layout_id);

        try {
            InputStream stream= getAssets().open("file.xml");
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document d=builder.parse(stream);
            Element element=d.getDocumentElement();
            element.normalize();

            NodeList nodeList=d.getElementsByTagName("students");
            for (int i=0;i<nodeList.getLength();i++)
            {
                TextView t1=new TextView(this);
                Node node=nodeList.item(i);
                if (node.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element element1=(Element)node;
                    t1.setTextSize(15);
                    t1.setTextColor(Color.BLACK);
                    t1.setText(t1.getText()+"name : "+getValue("name",element1)+"\n");
                    t1.setText(t1.getText()+"mobile:"+getValue("mobile",element1)+"\n");
                    t1.setText(t1.getText()+"course:"+getValue("course",element1)+"\n");
                    linearLayout.addView(t1);
                }
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public static String getValue(String name, Element element1) {
        NodeList node= element1.getElementsByTagName(name).item(0).getChildNodes();
        Node nodes=node.item(0);
        return nodes.getNodeValue();
    }
}