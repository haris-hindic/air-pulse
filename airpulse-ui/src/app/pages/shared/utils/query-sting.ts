export function getQueryString(
  params: any,
  prefix: string = '&',
  inRecursion: boolean = false
): string {
  let query: string = '';
  for (let key in params) {
    let value = params[key];

    if (inRecursion) {
      if (typeof key === 'number') {
        key = `[${key}]`;
      } else if (Array.isArray(value) || typeof value === 'object') {
        key = `.${key}`;
      } else {
        key = `.${key}`;
      }
    }

    if (
      typeof value === 'string' ||
      typeof value === 'number' ||
      typeof value === 'boolean'
    ) {
      let encoded = value;
      if (typeof value === 'string') {
        encoded = encodeURIComponent(value);
      }
      query += `${prefix}${key}=${encoded}`;
    } else if (value instanceof Date) {
      query += `${prefix}${key}=${value.toISOString()}`;
    } else if (Array.isArray(value) || typeof value === 'object') {
      if (Array.isArray(value)) {
        value = value.reduce(
          (acc, curr, index) => ((acc[index] = curr), acc),
          {}
        );
      }
      for (let k in value) {
        query += getQueryString({ [k]: value[k] }, `${prefix}${key}`, true);
      }
    }
  }
  return query;
}

export function serialize(obj: any) {
  var str = [];
  for (var p in obj)
    if (obj.hasOwnProperty(p)) {
      str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
    }
  return str.join("&");
}