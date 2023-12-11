import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    standalone: true,
    name: 'dateTimeDiff'
})
export class DateTimeDiff implements PipeTransform {
    transform(date1: Date, date2: Date): string {
        const seconds = (new Date(date2).getTime() - new Date(date1).getTime()) / 1000;
        console.log('seconds :>> ', seconds);
        const hours = Math.floor(seconds / 3600)
        const minutes = Math.floor((seconds % 3600) / 60)

        if (minutes === 0) {
            return `${hours} hr`
        }
        if (hours === 0) {
            return `${minutes} min`
        }
        return `${hours} hr : ${minutes} min`
    }
}