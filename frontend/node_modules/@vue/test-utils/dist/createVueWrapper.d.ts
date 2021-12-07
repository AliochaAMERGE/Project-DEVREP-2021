import { ComponentPublicInstance, App } from 'vue';
import { VueWrapper } from './vueWrapper';
export declare function createWrapper<T extends ComponentPublicInstance>(app: App | null, vm: ComponentPublicInstance, setProps?: (props: Record<string, unknown>) => void): VueWrapper<T>;
