package br.com.lucascruz.servico.rest.exemplo.bean;

/**
 * Este bean representa o registro da posição de um onibus
 * 
 * @author Lucas A Cruz
 *
 */
public class PosicaoOnibus {
	
	/*
	 * Data e Hora do registro da posição Ex:("06/07/2016 08:50")
	 */
	private String dataHora;
	/*
	 * Ordem do Onibus Ex:("B42593")
	 */
	private String ordem;
	/*
	 * Linha do Onibus Ex:("457")
	 */
	private String linha;
	/*
	 * Latitude Ex:(-22.88575)
	 */
	private Double latitude;
	/*
	 * Longitude Ex:(-43.300499)
	 */
	private Double longitude;
	/*
	 * Velocidade Ex:(49.0)
	 */
	private Double velocidade;
	/*
	 * Direção Ex:(25.0)
	 */
	private Integer direcao;

	/*
	 * Método Construtor
	 */
	private PosicaoOnibus() {
	}

	/*
	 * Métodos Sets e Gets
	 */
	
	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Double velocidade) {
		this.velocidade = velocidade;
	}

	public Integer getDirecao() {
		return direcao;
	}

	public void setDirecao(Integer direcao) {
		this.direcao = direcao;
	}

	/*
	 * Método To String
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PosicaoOnibus [dataHora=" + dataHora + ", ordem=" + ordem + ", linha=" + linha + ", latitude="
				+ latitude + ", longitude=" + longitude + ", velocidade=" + velocidade + ", direcao=" + direcao + "]";
	}

}
