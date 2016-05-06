package entity;

public class AttackEvent {
	
	// the damage
	public int damage;
	
	// whether the hit uses str/def or mag/res
	public boolean magical;
	
	// whether there was actually a hit
	public boolean hit;
	
	// whether there was a critical hit
	public boolean crit;
	
	// the source of the attack
	public AbstractEntity source;
	
	public AttackEvent(int damage, boolean magical, boolean hit, boolean crit, AbstractEntity source) {
		this.damage = damage;
		this.magical = magical;
		this.hit = hit;
		this.crit = crit;
		this.source = source;
	}
	
	public int damageAfterEffects() {
		return hit ? crit ? damage * 2 : damage : 0;
	}
	
}
