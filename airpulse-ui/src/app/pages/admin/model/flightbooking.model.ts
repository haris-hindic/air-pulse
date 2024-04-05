import { FlightResponse } from "./flight.model";

export class FlightBookingResponse {
  id!: number;
  totalPrice!: number;
  created!: Date;
  createdBy!: string;
  modified!: Date;
  modifiedBy!: string;
  userId!: number;
  userinfo!: string;
  status!: string;
  luggage!: string;
  seatClass!: string;
  flightId!: number;
  returnFlightId!: number;
  flight!: FlightResponse;
  returnFlight!: FlightResponse;
}

export class FlightBookingRequest {
  totalPrice!: number;
  userId!: number;
  status!: string;
  flightId!: number;
  returnFlightId!: number;
}