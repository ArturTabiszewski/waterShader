varying vec4      v_color;
varying vec2      v_texCoord0;

uniform vec4      u_color;
uniform sampler2D u_texture;
uniform sampler2D u_texture1;
uniform float     u_time;
uniform float     u_y_offset;
uniform float     u_sparkle_intensity;

void main()
{
    vec4 final_color = v_color;
    vec2 new_coord = v_texCoord0;

    // -----------------------------
    // STRETCH
    // -----------------------------
    // calculate the stretching factor
    float factor = ((0.5 - new_coord.x) * (new_coord.y - u_y_offset));
    // apply the factor and stretch the image
    new_coord.x += factor;

    final_color = (final_color) * texture2D(u_texture, new_coord);

    gl_FragColor = final_color;
    
    
}