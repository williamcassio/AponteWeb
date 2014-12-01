package utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rn.ItensRN;
import entity.Apontamento;
import entity.Itens;

public class GeraPlanilha {


	public void criarPlanilha(String localArquivo, Apontamento aponte) {
		int linha = 0;
		int coluna = 0;
		int numIni;
		int numFim;
		String texto;
		Workbook wb;
		Sheet sheet;
		CellStyle estilo;
		Cell cell;
		Row row;
		
		ItensRN itensRN = new ItensRN();
		
		List<Itens> lista = itensRN.listar(aponte);
		
		wb = criarPlanilha();
		sheet = criarAba("Apontamento", wb);
		texto = "Aponte_" + aponte.getUsuario().getMatricula() + "_" + aponte.getUsuario().getLogin();
		row = sheet.createRow(linha); 		
		cell = inserirCelulaTexto(linha, coluna, texto, sheet, row);
		estilo = fonteNegritoVermelho(null, wb);
		cell.setCellStyle(estilo);
		
		linha = 1;
		row = sheet.createRow(linha);
		coluna = 6;
		cell = inserirCelulaTexto(linha, coluna, "Tempo Trabalhado", sheet, row);
		
		estilo = fonteNegrito(null, wb);
		estilo = alinhamentoADireita(estilo, wb);
		cell.setCellStyle(estilo);
		
		// Inserindo colunas
		
		linha = 3;
		row = sheet.createRow(linha);
		coluna = 0;
		cell = inserirCelulaTexto(linha, coluna, "Técnico", sheet, row);
		estilo = fonteNegrito(null, wb);
		estilo = estiloBorda(estilo, wb);
		estilo = alinhamentoCentralizado(estilo, wb);
		estilo = alinhamentoCentralizadoVertical(estilo, wb);
		cell.setCellStyle(estilo);
		coluna = 1;
		cell = inserirCelulaTexto(linha, coluna, "Tipo", sheet, row);
		estilo = fonteNegrito(null, wb);
		estilo = estiloBorda(estilo, wb);
		estilo = alinhamentoCentralizado(estilo, wb);
		estilo = alinhamentoCentralizadoVertical(estilo, wb);
		cell.setCellStyle(estilo);
		coluna = 2;
		cell = inserirCelulaTexto(linha, coluna, "ID", sheet, row);
		estilo = fonteNegrito(null, wb);
		estilo = estiloBorda(estilo, wb);
		estilo = alinhamentoCentralizado(estilo, wb);
		estilo = alinhamentoCentralizadoVertical(estilo, wb);
		cell.setCellStyle(estilo);
		coluna = 3;
		cell = inserirCelulaTexto(linha, coluna, "Fase", sheet, row);
		estilo = fonteNegrito(null, wb);
		estilo = estiloBorda(estilo, wb);
		estilo = alinhamentoCentralizado(estilo, wb);
		estilo = alinhamentoCentralizadoVertical(estilo, wb);
		cell.setCellStyle(estilo);
		coluna = 4;
		cell = inserirCelulaTexto(linha, coluna, "Atividade", sheet, row);
		estilo = fonteNegrito(null, wb);
		estilo = estiloBorda(estilo, wb);
		estilo = alinhamentoCentralizado(estilo, wb);
		estilo = alinhamentoCentralizadoVertical(estilo, wb);
		cell.setCellStyle(estilo);
		coluna = 5;
		cell = inserirCelulaTexto(linha, coluna, "Data", sheet, row);
		estilo = alinhamentoCentralizadoVertical(estilo, wb);
		estilo = fonteNegrito(null, wb);
		estilo = estiloBorda(estilo, wb);
		estilo = alinhamentoCentralizado(estilo, wb);
		estilo = alinhamentoCentralizadoVertical(estilo, wb);
		cell.setCellStyle(estilo);		
		coluna = 6;
		cell = inserirCelulaTexto(linha, coluna, "Tempo Utilizado(h)", sheet, row);
		estilo = textoQuebrado(null, wb);
		estilo = estiloBorda(estilo, wb);
		estilo = alinhamentoCentralizado(estilo, wb);
		estilo = alinhamentoCentralizadoVertical(estilo, wb);
		estilo = fonteNegrito(estilo, wb);
		cell.setCellStyle(estilo);
		
		
		linha = 4;
		numIni = linha+1;
		for (Itens itens : lista){
			row = sheet.createRow(linha);
			cell = inserirCelulaTexto(linha, 0, aponte.getUsuario().getMatricula() + "-"+ aponte.getUsuario().getLogin().toUpperCase(), sheet, row);
			estilo = estiloBorda(null, wb);
			cell.setCellStyle(estilo);
			cell = inserirCelulaTexto(linha, 1, itens.getTipo().getDescricao().toUpperCase(), sheet, row);
			estilo = estiloBorda(null, wb);
			cell.setCellStyle(estilo);	
			cell = inserirCelulaTexto(linha, 2, itens.getIdChamado().toUpperCase(), sheet, row);
			estilo = estiloBorda(null, wb);
			cell.setCellStyle(estilo);
			cell = inserirCelulaTexto(linha, 3, itens.getFase().toUpperCase(), sheet, row);
			estilo = estiloBorda(null, wb);
			cell.setCellStyle(estilo);			
			cell = inserirCelulaTexto(linha, 4, itens.getDescricao().toUpperCase(), sheet, row);
			estilo = textoQuebrado(null, wb);
			estilo = estiloBorda(estilo, wb);
			cell.setCellStyle(estilo);
			cell = inserirCelulaData(linha, 5, aponte.getData(), sheet, row);
			estilo = estiloBorda(null, wb);
			cell.setCellStyle(estilo);			
			cell = inserirCelulaNumerica(linha, 6, itens.getDuracao(), sheet, row);
			estilo = estiloBorda(null, wb);
			estilo = formatoNumerico(estilo, wb);
			cell.setCellStyle(estilo);
			linha++;
		}
		numFim = linha;
		String ref = "G"+numIni+":G"+ numFim;
		linha = 2;
		coluna = 6;
		row = sheet.createRow(linha);
		cell = inserirCelulaNumerica(linha, coluna, Float.parseFloat("0"), sheet, row);
		cell.setCellFormula("SUM("+ref+")");
		estilo = formatoNumerico(null, wb);
		cell.setCellStyle(estilo);
		
		// setando tamanho de colunas
		setarTamanhoColuna(sheet, 20, 0);
		setarTamanhoColuna(sheet, 15, 1);
		setarTamanhoColuna(sheet, 15, 2);
		setarTamanhoColuna(sheet, 18, 3);
		setarTamanhoColuna(sheet, 120, 4);
		setarTamanhoColuna(sheet, 12, 5);
		setarTamanhoColuna(sheet, 15, 6);
		
        gravarArquivo(wb, localArquivo);
        

       
	}
	
	public void gravarArquivo(Workbook wb, String localArquivo){
        FileOutputStream out;
		try {
			out = new FileOutputStream(localArquivo);
			wb.write(out);
	        out.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public Workbook criarPlanilha(){
		Workbook wb = new XSSFWorkbook();
		return wb;
	}
	
	public Sheet criarAba(String nomeAba, Workbook wb){
		Sheet sheet = wb.createSheet(nomeAba);
		return sheet;
	}
	
	
	public CellStyle formatoNumerico(CellStyle estilo, Workbook wb){
		if (estilo == null){
			  estilo = wb.createCellStyle();
			}
		estilo.setDataFormat(wb.createDataFormat().getFormat("##,##0.00"));
		return estilo;
	}
	
	public CellStyle alinhamentoCentralizadoVertical(CellStyle estilo, Workbook wb){
		if (estilo == null){
			  estilo = wb.createCellStyle();
			}
		estilo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return estilo;
	}	
	
	public CellStyle alinhamentoCentralizado(CellStyle estilo, Workbook wb){
		if (estilo == null){
			  estilo = wb.createCellStyle();
			}
		estilo.setAlignment(CellStyle.ALIGN_CENTER);
		return estilo;
	}
	
	public CellStyle textoQuebrado(CellStyle estilo, Workbook wb){
		if (estilo == null){
			  estilo = wb.createCellStyle();
			}
		estilo.setWrapText(true);
		return estilo;
	}
	
	public CellStyle alinhamentoADireita(CellStyle estilo, Workbook wb){
		if (estilo == null){
			  estilo = wb.createCellStyle();
			}
		estilo.setAlignment(CellStyle.ALIGN_RIGHT);
		return estilo;
	}	
	
	public CellStyle alinhamentoAEsquerda(CellStyle estilo, Workbook wb){
		if (estilo == null){
			  estilo = wb.createCellStyle();
			}
		estilo.setAlignment(CellStyle.ALIGN_LEFT);
		return estilo;
	}	
	
	public CellStyle fonteNegrito(CellStyle estilo, Workbook wb){
		if (estilo == null){
		  estilo = wb.createCellStyle();
		}
		Font font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		estilo.setFont(font);
		return estilo;
	}
	
	public CellStyle fonteNegritoVermelho(CellStyle estilo, Workbook wb){
		if (estilo == null){
			estilo = wb.createCellStyle();
		}
		Font font = wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		estilo.setFont(font);
		return estilo;
	}	
	
	public CellStyle estiloBorda(CellStyle estilo, Workbook wb){
		if (estilo == null){
			estilo = wb.createCellStyle();
		}
        estilo.setBorderRight(CellStyle.BORDER_THIN);
        estilo.setBorderLeft(CellStyle.BORDER_THIN);
        estilo.setBorderTop(CellStyle.BORDER_THIN);
        estilo.setBorderBottom(CellStyle.BORDER_THIN);
        return estilo;
	}
	
	public Cell inserirCelulaTexto(int linha, int coluna, String texto, Sheet sheet, Row row){
		Cell cell2;
        cell2 = row.createCell(coluna);
        cell2.setCellValue(texto);
        return cell2;
      
	}
	
	public Cell inserirCelulaNumerica(int linha, int coluna, Float numero, Sheet sheet, Row row){
		Cell cell2;
        cell2 = row.createCell(coluna);
        cell2.setCellValue(numero);
        return cell2;
	}

	public Cell inserirCelulaData(int linha, int coluna, Date data, Sheet sheet, Row row){
		Cell cell2;
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = sf.format(data);
        cell2 = row.createCell(coluna);
        cell2.setCellValue(dataFormatada);
        return cell2;
	}	
	
	
	public void setarTamanhoColuna(Sheet sheet, int tamanho, int coluna){
		sheet.setColumnWidth(coluna, tamanho * 256); 
	}
}