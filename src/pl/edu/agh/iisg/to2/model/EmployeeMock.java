package pl.edu.agh.iisg.to2.model;

import java.math.BigDecimal;

public class EmployeeMock implements IEmployee {
	private String firstName;
	private String lastName;
	
	public EmployeeMock(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#getFirstName()
	 */
    @Override
	public String getFirstName() {
    	return firstName;
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#setFirstName(java.lang.String)
	 */
    @Override
	public void setFirstName(String firstName) {
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#getLastName()
	 */
    @Override
	public String getLastName() {
    	return lastName;
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#setLastName(java.lang.String)
	 */
    @Override
	public void setLastName(String lastName) {
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#getPosition()
	 */
    @Override
	public String getPosition() {
    	return null;
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#setPosition(java.lang.String)
	 */
    @Override
	public void setPosition(String position) {
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#getSalary()
	 */
    @Override
	public int getSalary() {
    	return 0;
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#setSalary(int)
	 */
    @Override
	public void setSalary(int salary) {
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#getPhone()
	 */
    @Override
	public String getPhone() {
    	return null;
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#setPhone(java.lang.String)
	 */
    @Override
	public void setPhone(String phone) {
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#getDate()
	 */
    @Override
	public String getDate() {
    	return null;
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#setDate(java.lang.String)
	 */
    @Override
	public void setDate(String date) {
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#getEmail()
	 */
    @Override
	public String getEmail() {
    	return null;
    }

    /* (non-Javadoc)
	 * @see com.agh.to2.mock.IEmployee#setEmail(java.lang.String)
	 */
    @Override
	public void setEmail(String email) {
    }

	@Override
	public String getId() {
		return firstName + " " + lastName;
	}

	@Override
	public void setId(BigDecimal id) {
		// TODO Auto-generated method stub
		
	}
}