
import { Cube, CubeLevel } from './cube/Cube.js';
import { Dock, DockItem } from './dock/Dock.js';



/*
const CARBIDE_ROOT_CODE = "0206";
*/

export const CARBIDE_BOHR_TYPE_CODES = {

    // cube
    "02060102" : Cube,
    "02060103": CubeLevel,

    // dock
    "02060202": Dock,
    "02060203": DockItem
};