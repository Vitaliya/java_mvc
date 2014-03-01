CREATE LANGUAGE plpgsql;

-- ТРИГГЕРЫ -- 

-- При добавлении путевки, подсчитывем конечную сумму -- 
CREATE OR REPLACE FUNCTION addPermit() RETURNS TRIGGER AS $$
DECLARE
	cr_season CURSOR(tid Season.id%TYPE) IS SELECT Season.percent_sum FROM Season WHERE Season.id = tid; 
	cr_transport CURSOR(tid Transport.id%TYPE) IS SELECT Transport.sum_transport FROM Transport WHERE Transport.id = tid;
	cr_room CURSOR(tid Room.id%TYPE) IS SELECT Room.cost, Room.full_room FROM Room WHERE Room.id = tid;
	percent_sum Season.percent_sum%TYPE;
	sum_transport Transport.sum_transport%TYPE;
	cost Room.cost%TYPE;
	oldFullRoom Room.full_room%TYPE;
	newFullRoom Room.full_room%TYPE;
BEGIN
	OPEN cr_room(NEW.id_room);
	FETCH cr_room INTO cost, oldFullRoom;
	CLOSE cr_room;
	newFullRoom = oldFullRoom - 1;
	IF newFullRoom < 0 THEN
		RAISE EXCEPTION 'No availability'; 
	END IF;
	UPDATE Room SET full_room = newFullRoom WHERE Room.id = NEW.id_room; 
	OPEN cr_season(NEW.id_season);
	FETCH cr_season INTO percent_sum;
	CLOSE cr_season;
	OPEN cr_transport(NEW.id_transport);
	FETCH cr_transport INTO sum_transport;
	CLOSE cr_transport;
	NEW.final_sum = (NEW.visa + NEW.period * cost + sum_transport) * (100.0 + percent_sum) / 100.0;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER add_permit BEFORE INSERT ON Permit FOR EACH ROW EXECUTE PROCEDURE addPermit();







