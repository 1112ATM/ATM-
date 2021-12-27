package com.util;

import java.io.*;
import javax.swing.JTable;
import javax.swing.table.TableModel;

//导出excel
public class ExcelExporter
{  //设置excel表的列数与列名
    public void exportTable(JTable table, File file) throws IOException
    {
        TableModel model = table.getModel();
        BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < model.getColumnCount(); i++) {
            bWriter.write(model.getColumnName(i));
            bWriter.write("\t");
        }
        bWriter.newLine();  //通过循环行与列输入数据
        for (int i = 0; i < model.getRowCount(); i++)
        {
            for (int j = 0; j < model.getColumnCount(); j++)
            {
                bWriter.write(model.getValueAt(i, j).toString());
                bWriter.write("\t");
            }
            bWriter.newLine();
        }
        bWriter.close();
    }
}
