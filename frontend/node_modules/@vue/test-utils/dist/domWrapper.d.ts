import BaseWrapper from './baseWrapper';
import WrapperLike from './interfaces/wrapperLike';
import { ComponentPublicInstance } from 'vue';
import { FindAllComponentsSelector, FindComponentSelector } from './types';
import type { createWrapper, VueWrapper } from './vueWrapper';
export declare class DOMWrapper<ElementType extends Element> extends BaseWrapper<ElementType> implements WrapperLike {
    private createVueWrapper;
    constructor(element: ElementType, createVueWrapper: typeof createWrapper);
    isVisible(): boolean;
    html(): string;
    find<K extends keyof HTMLElementTagNameMap>(selector: K): DOMWrapper<HTMLElementTagNameMap[K]>;
    find<K extends keyof SVGElementTagNameMap>(selector: K): DOMWrapper<SVGElementTagNameMap[K]>;
    find<T extends Element>(selector: string): DOMWrapper<T>;
    findAll<K extends keyof HTMLElementTagNameMap>(selector: K): DOMWrapper<HTMLElementTagNameMap[K]>[];
    findAll<K extends keyof SVGElementTagNameMap>(selector: K): DOMWrapper<SVGElementTagNameMap[K]>[];
    findAll<T extends Element>(selector: string): DOMWrapper<T>[];
    findComponent<T extends ComponentPublicInstance>(selector: FindComponentSelector | (new () => T)): VueWrapper<T>;
    findAllComponents(selector: FindAllComponentsSelector): VueWrapper<any>[];
    private setChecked;
    setValue(value?: any): Promise<void>;
    private setSelected;
}
