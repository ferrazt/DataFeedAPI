/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jhoel
 */
public class AtivoTableModel extends AbstractTableModel
{
    private List<Produto> dados = new ArrayList<>();
    private String[] colunas = {"Símbolo", "Ult.", "%", "Neg", "Pex","DtVc","QtdC","QtdV","Venda","Tp Opção","Op.Ref" };

   

    @Override
    public String getColumnName(int column) {
        return colunas[column];

    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int colunas) {
        switch(colunas){
            case 0:
                return dados.get(linha).getSimbolo();
            case 1:
                return dados.get(linha).getUlt();
            case 2:
                return dados.get(linha).getPorcent();
            case 3:
                return dados.get(linha).getNeg();
            case 4:
                return dados.get(linha).getPex();
            case 5:
                return dados.get(linha).getDtVc();
            case 6:
                return dados.get(linha).getQtdC();
            case 7:
                return dados.get(linha).getQtdV();
            case 8:
                return dados.get(linha).getVenda();
            case 9:
                return dados.get(linha).getTpOpcao();
            case 10:
                return dados.get(linha).getOpRef();
                
        }
      return null;
    }

    @Override
    public int getRowCount() {
    return dados.size();
    }
    
}
