package es.excibit.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


/**
 * The Class XpayMapper.
 */
@SuppressWarnings("rawtypes")
public final class ApiMapper implements RowMapper {

  /**
   * Map row.
   *
   * @param rs the rs
   * @param rowNum the row num
   * @return the object
   * @throws SQLException the SQL exception
   */
  @Override
  public Object mapRow(final ResultSet rs, final int rowNum) throws SQLException {

    return rs.getString("ID");

  }


}
