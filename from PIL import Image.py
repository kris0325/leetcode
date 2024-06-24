from PIL import Image

# Open the uploaded image
input_path = "/Users/kris/Downloads/IMG_5631.JPG"
img = Image.open(input_path)

# Define the target size for the China passport photo (in pixels)
# Standard passport photo size for China: 33mm x 48mm
# Assuming a common resolution of 300 DPI (dots per inch), we convert mm to pixels
dpi = 300
width_mm = 33
height_mm = 48
width_px = int(width_mm * dpi / 25.4)
height_px = int(height_mm * dpi / 25.4)

# Resize the image to the target size
resized_img = img.resize((width_px, height_px), Image.LANCZOS)

# Save the resized image
output_path = "/Users/kris/Downloads/IMG_5631-passport.JPG"
resized_img.save(output_path)

print("Resized image saved at:", output_path)
