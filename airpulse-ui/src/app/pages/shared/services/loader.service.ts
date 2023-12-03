import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root',
})
export class LoaderService {
    private _selector = 'load-wrapper';
    private _element: HTMLElement | null;

    constructor() {
        this._element = document.getElementById(this._selector);
    }

    public show(): void {
        this._element!.style['display'] = 'block';
    }

    public hide(delay: number = 0): void {
        setTimeout(() => {
            this._element!.style['display'] = 'none';
        }, delay);
    }
}
