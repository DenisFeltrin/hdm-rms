package de.hdm.rms.server.db;

import java.sql.Connection;
import java.sql.Statement;
import de.hdm.rms.shared.bo.Reservation;
import de.hdm.rms.shared.bo.Room;

public class ReservationMapper {

	private static ReservationMapper reservationMapper = null;

	protected ReservationMapper() {
	}

	public static ReservationMapper reservationMapperv() {

		if (reservationMapper == null) {
			reservationMapper = new ReservationMapper();

		}
		return reservationMapper;
	}

	public void insertReservation(Reservation re) {
		Connection con = DatebaseConnection.connection();
		try {
			Statement state = con.createStatement();
			String sqlquery = "INSERT INTO Reservation (HostId, Topic, RoomId, Length, StartTime) VALUES ("
					+ "'"
					+ re.getOrganisatorId()
					+ "','"
					+ re.getTopic()
					+ "','"
					+ re.getRoomId()
					+ "','"
					+ re.getLength()
					+ "','"
					+ re.getStartTime()
					+ "') ;";
			state.executeUpdate(sqlquery);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}