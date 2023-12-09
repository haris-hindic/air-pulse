export const transformImage = (image: string) => {
  return 'data:image/jpeg;base64,' + image;
};

export const imgToByte = (url: string): Promise<Uint8Array> => {
  return new Promise((resolve, reject) => {
    fetch(url)
      .then((response) => response.blob())
      .then((blob) => {
        const reader = new FileReader();
        reader.onloadend = () => {
          const byteArray = new Uint8Array(reader.result as ArrayBuffer);
          resolve(byteArray);
        };
        reader.onerror = (err) => {
          reject(err);
        };
        reader.readAsArrayBuffer(blob);
      })
      .catch((error) => {
        console.error('There was an error fetching the blob:', error);
        reject(error);
      });
  });
};
