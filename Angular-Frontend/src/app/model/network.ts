export class Network {
  id: number;
  netName: string;
  securityNumber: string;

  constructor(id: number, network: string, numb: string) {
    this.id = id;
    this.netName = network;
    this.securityNumber = numb;
  }
}
