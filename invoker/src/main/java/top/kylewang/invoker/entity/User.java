package top.kylewang.invoker.entity;

/**
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月02日
 */
public class User {

	private long userid;
	private String name;
	private String card;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
