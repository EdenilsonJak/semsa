package com.ejsistemas.semsa.grafico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import com.ejsistemas.semsa.model.vo.MesAnoVO;
import com.ejsistemas.semsa.repository.InternacaoRepository;

@Named
@RequestScoped
public class GraficoInternacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	InternacaoRepository internacaoRepository;

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Responsivo");
	EntityManager manager = factory.createEntityManager();

	MesAnoVO mesAnoVo;
	List<MesAnoVO> list;

	SQLQuery query;
	Calendar calendar = Calendar.getInstance();

	String sql;

	public GraficoInternacao() {
		criarLineChartModelInternacao();
		mesAnoVo = new MesAnoVO();
	}

	private LineChartModel lineChartModelInternacao;
	private LineChartModel lineChartModelTransferencia;

	public LineChartModel getLineChartModelInternacao() {
		return lineChartModelInternacao;
	}

	public LineChartModel getLineChartModelTransferencia() {
		return lineChartModelTransferencia;
	}

	private void criarLineChartModelInternacao() {
		lineChartModelInternacao = initMesAnoModelInternacao();
		lineChartModelInternacao.setTitle("Histórico de Movimentação");
		lineChartModelInternacao.setLegendPosition("e");
		lineChartModelInternacao.setShowPointLabels(true);
		lineChartModelInternacao.getAxes().put(AxisType.X, new CategoryAxis("Mês - Ano"));
		lineChartModelInternacao.setAnimate(true);

		Axis yAxis = lineChartModelInternacao.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidades");
		yAxis.setMin(0);
		// yAxis.setMax(500);
	}

	public LineChartModel initMesAnoModelInternacao() {
		list = new ArrayList<>();
		int mes = calendar.get(Calendar.MONTH);

		LineChartModel model = new LineChartModel();
		ChartSeries internacao = new ChartSeries();

		internacao.setLabel("Internação");

		Session session = manager.unwrap(Session.class);

		/*
		 * sql = new String(
		 * "select date_format(bd_responsivo.tb_internacao.dataInternacao, '%m-%y') as mesAno, count(*) as qtde from tb_internacao where extract(month from dataInternacao) = extract(month from current_date()) and status <> 'CANCELADO'"
		 * );
		 * 
		 * query = session.createSQLQuery(sql); mesAnoVo = (MesAnoVO)
		 * query.setResultTransformer(Transformers.aliasToBean(MesAnoVO.class)).
		 * uniqueResult();
		 * 
		 * list.add(mesAnoVo); mesAnoVo = new MesAnoVO();
		 */

		for (int i = mes; i > -1; i--) {
			if (i == 0) {
				sql = new String(
						"select date_format(dataInternacao, '%m-%Y') as mesAno, count(*) as qtde from tb_internacao where extract(year_month from dataInternacao) = extract(year_month from current_date()) and status <> 'CANCELADO'");
				query = session.createSQLQuery(sql);
				mesAnoVo = (MesAnoVO) query.setResultTransformer(Transformers.aliasToBean(MesAnoVO.class))
						.uniqueResult();

				list.add(mesAnoVo);
				mesAnoVo = new MesAnoVO();
			} else {
				sql = new String(
						"select date_format(dataInternacao, '%m-%Y') as mesAno, count(*) as qtde from tb_internacao where extract(year_month from dataInternacao) = extract(year_month from current_date()) :mes and status <> 'CANCELADO'");
				query = session.createSQLQuery(sql);
				query.setParameter("mes", -i);
				mesAnoVo = (MesAnoVO) query.setResultTransformer(Transformers.aliasToBean(MesAnoVO.class))
						.uniqueResult();

				list.add(mesAnoVo);
				mesAnoVo = new MesAnoVO();
			}
		}

		for (int i = 0; i < list.size(); i++) {
			mesAnoVo = list.get(i);
			internacao.set(mesAnoVo.getMesAno(), mesAnoVo.getQtde());
		}

		list = new ArrayList<>();
		LineChartModel modelTransferencia = new LineChartModel();
		ChartSeries transferencia = new ChartSeries();

		transferencia.setLabel("Transferência");
		
		for (int i = mes; i > -1; i--) {
			if(i == 0){
				sql = new String(
						"select date_format(dataAlta, '%m-%Y') as mesAno, count(*) as qtde from tb_internacao where extract(year_month from dataAlta) = extract(year_month from current_date()) and status = 'TRANSFERIDO'");
				query = session.createSQLQuery(sql);
				mesAnoVo = (MesAnoVO) query.setResultTransformer(Transformers.aliasToBean(MesAnoVO.class)).uniqueResult();
				list.add(mesAnoVo);
				mesAnoVo = new MesAnoVO();
			}
			else{
				sql = new String(
					"select date_format(dataAlta, '%m-%Y') as mesAno, count(*) as qtde from tb_internacao where extract(year_month from dataAlta) = extract(year_month from current_date()):mes and status = 'TRANSFERIDO'");
			query = session.createSQLQuery(sql);
			query.setParameter("mes", -i);
			mesAnoVo = (MesAnoVO) query.setResultTransformer(Transformers.aliasToBean(MesAnoVO.class)).uniqueResult();

			list.add(mesAnoVo);
			mesAnoVo = new MesAnoVO();
			}
		}

		for (int i = 0; i < list.size(); i++) {
				mesAnoVo = list.get(i);
				transferencia.set(mesAnoVo.getMesAno(), mesAnoVo.getQtde());
		}

		list = new ArrayList<>();
		LineChartModel modelAlta = new LineChartModel();
		ChartSeries alta = new ChartSeries();

		alta.setLabel("Alta");
		SQLQuery sqlQuery;

		for (int i = mes; i > -1; i--) {
			if(i ==0 ){
				sql = new String(
						"select date_format(dataAlta, '%m-%Y') as mesAno, count(*) as qtde from tb_internacao where extract(year_month from dataAlta) = extract(year_month from current_date()) and status = 'ALTA'");
				query = session.createSQLQuery(sql);
				mesAnoVo = (MesAnoVO) query.setResultTransformer(Transformers.aliasToBean(MesAnoVO.class)).uniqueResult();
				list.add(mesAnoVo);
				mesAnoVo = new MesAnoVO();
			}
			else{
				sql = new String(
					"select date_format(dataAlta, '%m-%Y') as mesAno, count(*) as qtde from tb_internacao where extract(year_month from dataAlta) = extract(year_month from current_date()):mes and status = 'ALTA'");
			query = session.createSQLQuery(sql);
			query.setParameter("mes", -i);
			mesAnoVo = (MesAnoVO) query.setResultTransformer(Transformers.aliasToBean(MesAnoVO.class)).uniqueResult();

			list.add(mesAnoVo);
			mesAnoVo = new MesAnoVO();
			}
			
		}

		for (int i = 0; i < list.size(); i++) {
				mesAnoVo = list.get(i);
				alta.set(mesAnoVo.getMesAno(), mesAnoVo.getQtde());
		}

		model.addSeries(internacao);
		model.addSeries(transferencia);
		model.addSeries(alta);
		manager.close();
		return model;

	}

}
