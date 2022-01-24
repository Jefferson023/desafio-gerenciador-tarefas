import { ModuleWithProviders } from "@angular/core";
import { RouterModule } from "@angular/router";
import { CadastroComponent } from "./cadastro/cadastro.component";
import { ListagemComponent } from "./listagem/listagem.component";

export const rotas: ModuleWithProviders<RouterModule> = RouterModule.forChild([
    {
        path: ``,
        component: ListagemComponent
    },
    {
        path: `cadastro`,
        component: CadastroComponent,
    },
    {
         path: `cadastro/:id`,
         component: CadastroComponent,
    }
]);
