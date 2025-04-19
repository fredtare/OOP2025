import { Product } from "./Product"
import { WebshopUser } from "./WebshopUser"

export type WebshopOrder= {
    id: number,
    name: string,
    created: string,
    webshopUser: WebshopUser,
    products: Product[],
    totalSum: number
}