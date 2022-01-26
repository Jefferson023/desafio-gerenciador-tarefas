import { User } from "./User";

export interface Task {
    numero: number;
	titulo: string;
	descricao:string;
	prioridade: string;
	deadline: Date;
	responsavel: User;
	situacao: string;
}