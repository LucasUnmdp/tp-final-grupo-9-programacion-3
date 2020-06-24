package Pokemones.modelo;

/**
 * @author Grupo 9
 * <br>
 * Clase padre de todos los Pokemones. Mantiene la xp, nombre y estadisticas.
 */
public abstract class Pokemon implements IHechizable,Cloneable,IClasificable{
	private String nombre;
	private int xp;
	protected double vitalidad,escudo,fuerza;
	
	protected Pokemon(String nombre, double vitalidad,double escudo, double fuerza) {
		this.nombre=nombre;
		this.vitalidad=vitalidad;
		this.escudo=escudo;
		this.fuerza=fuerza;
		this.xp=0;
	}
	public boolean muerto() {
		return this.vitalidad<=0;
	}
	public String getNombre() {
		return nombre;
	}
	public void addXp(int xp) {
		this.xp+=xp;
	}
	public int getXp() {
		return xp;
	}

	/**
	 * Realiza la secuencia Golpe Inicial > Recarga > Golpe final aplicando todo daño al parametro.
	 * <b>Pre:</b> adversario debe ser distinto de null.
	 * @param adversario
	 */
	public void ataca(Pokemon adversario) {
		System.out.println(this.nombre+" ejecuta golpe Inicial");
		golpeInicial(adversario);
		System.out.println(this.nombre+" ejecuta Recarga");
		recarga();
		System.out.println(this.nombre+" ejecuta golpe Final");
		golpeFinal(adversario);
	}

	/**
	 * Aplica daño al adversario igual a la fuerza de este Pokemon sobre 2.
	 * <b>Pre:</b> adversario debe ser distinto de null.
	 * @param adversario Pokemon al que se le aplica el daño.
	 */
	private void golpeInicial(Pokemon adversario) {
		adversario.recibeDamage(this.fuerza);
		this.fuerza/=2;
	}

	/**
	 * Hook para aquellos pokemones que tengan una recarga (opcional).
	 */
	protected void recarga() {
		System.out.println("No logro recargar");
	}

	protected abstract void golpeFinal(Pokemon adversario);
	protected abstract void recibeDamage(double damage);

	/**
	 * De acuerdo al tipo de Pokemon hijo de esta clase, puede ser clonable o no.
	 * @return De ser posible un clon exacto de este Pokemon. En caso contrario, null.
	 * @throws CloneNotSupportedException si el tipo de Pokemon no admite clonacion.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Devuelve la categoria de este Pokemon de acuerdo a la siguiente tabla:<br>
	 *     <table>
	 *         <tr>
	 *             <th>XP Minima</th>
	 *             <th>Categoria</th>
	 *         </tr>
	 *         <tr>
	 *             <td>0</td>
	 *             <td>Nivel 0</td>
	 *         </tr>
	 *         <tr>
	 * 	           <td>3</td>
	 * 	           <td>Nivel 1</td>
	 * 	       </tr>
	 * 	       <tr>
	 * 	           <td>6</td>
	 * 	           <td>Nivel 2</td>
	 * 	       </tr>
	 * 	       <tr>
	 * 	           <td>9</td>
	 * 	           <td>Nivel 3</td>
	 * 	       </tr>
	 *     </table>
	 * @return String representando la categoria.
	 */
	@Override
	public String getCategoria() {
		String retorno;
		if(this.xp<=3) {
			retorno="nivel 0";
		}else if(this.xp<=6) {
			retorno="nivel 1";
		}else if(this.xp<=9) {
			retorno="nivel 2";
		}else 
			retorno="nivel 3";
		return retorno;
	}
	
}
