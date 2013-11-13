package br.com.caelum.agiletickets.models;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	
	@Test
	public void deveCriarSessaoNoHorarioDevido() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate dataInicioEFim = new LocalDate(2013, 01, 01);
		LocalTime horario = new LocalTime(14, 0, 0);
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicioEFim, dataInicioEFim, horario, Periodicidade.DIARIA);
		Assert.assertEquals(dataInicioEFim.toDateTime(horario), sessoes.get(0).getInicio());
	}
	
	@Test
	public void deveCriarSessaoNoPeriodoDevido() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate dataInicio = new LocalDate(2013, 11, 17);
		LocalDate dataFim = new LocalDate(2013, 11, 30);
		LocalTime horario = new LocalTime(14, 0, 0);
		List<Sessao> sessoes = espetaculo.criaSessoes(dataInicio, dataFim, horario, Periodicidade.DIARIA);
		Assert.assertFalse(sessoes.get(0).getInicio().toLocalDate().isAfter(dataInicio)
				&& sessoes.get(0).getFim().toLocalDate().isBefore(dataFim));
		Assert.assertTrue(false);
	}
	
	@Test
	public void deveCriarSessaoSemanalValida() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		
	}
		
	
}
