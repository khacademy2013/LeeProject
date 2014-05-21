
public class dataVO {
	private String group;
	private String name;
	private String cell;
	private String email;
	private String company;
	private String position;
	private String addr;
	
	public dataVO() {
		this(null,"이름없음",null,null,null,null,null);
	}

	public dataVO(String group, String name) {
		this(group,name,null,null,null,null,null);
	}

	public dataVO(String group, String name, String cell) {
		this(group,name,cell,null,null,null,null);
	}

	public dataVO(String group, String name, String cell,String email) {
		this(group,name,cell,email,null,null,null);
	}

	public dataVO(String group, String name, String cell,String email, String company) {
		this(group,name,cell,email,company,null,null);
	}
	
	public dataVO(String group, String name, String cell,String email, String company,String position) {
		this(group,name,cell,email,company,position,null);
	}

	public dataVO(String group, String name, String cell,String email, String company,String position, String addr) {
		super();
		this.group = group;
		this.name = name;
		this.cell = cell;
		this.email= email;
		this.company = company;
		this.position = position;
		this.addr = addr;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + ((cell == null) ? 0 : cell.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		dataVO other = (dataVO) obj;
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
		if (cell == null) {
			if (other.cell != null)
				return false;
		} else if (!cell.equals(other.cell))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "dataVO [group=" + group + ", name=" + name + ", cell=" + cell
				+ ", email=" + email + ", company=" + company + ", position="
				+ position + ", addr=" + addr + "]";
	}

	
}
